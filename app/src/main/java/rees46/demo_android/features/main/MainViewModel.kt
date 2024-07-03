package rees46.demo_android.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import com.personalizatio.api.entities.product.ProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.product.ProductUtils

class MainViewModel(
    private val sdk: SDK
) : ViewModel() {

    private val _searchResultItems: MutableSharedFlow<MutableList<Product>> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val searchResultItems: Flow<MutableList<Product>> = _searchResultItems

    fun searchProduct(query: String = "") {

        if (query.isEmpty()) emptySearch()
        else {
            sdk.searchManager.searchInstant(
                query = query,
                onGetSearchInstant = { searchInstantEntity ->
                    handleProductResult(searchInstantEntity.products)
                }
            )
        }
    }

    private fun emptySearch() {
        sdk.searchManager.searchBlank(
            onGetSearchBlank = { searchBlankEntity ->
                handleProductResult(searchBlankEntity.products)
            }
        )
    }

    private fun handleProductResult(searchProductsResult: List<ProductEntity>) {
        val searchResultList = mutableListOf<Product>()
        for (i in searchProductsResult) {
            val product = ProductUtils.createProduct(i)
            searchResultList.add(product)
        }
        viewModelScope.launch {
            _searchResultItems.emit(searchResultList)
        }
    }
}

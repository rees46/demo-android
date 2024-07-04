package rees46.demo_android.features.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import com.personalizatio.api.entities.search.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.entity.productsEntity.ProductEntity
import rees46.demo_android.features.product.createProduct

class MainViewModel(
    private val sdk: SDK
) : ViewModel() {

    private val _searchResultItems: MutableSharedFlow<MutableList<ProductEntity>> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val searchResultItems: Flow<MutableList<ProductEntity>> =
        _searchResultItems

    private val _searchResultCategoriesItems: MutableSharedFlow<MutableList<CategoryEntity>> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val searchResultCategoriesItems: Flow<MutableList<CategoryEntity>> =
        _searchResultCategoriesItems

    fun searchProduct(query: String = "") {
        if (query.isEmpty()) emptySearch()
        else {
            sdk.searchManager.searchInstant(
                query = query,
                onGetSearchInstant = { searchInstantEntity ->
                    handleProductResult(searchInstantEntity.products)
                    handleCategoriesResult(searchInstantEntity.categories)
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

    private fun handleProductResult(searchProductsResult: List<com.personalizatio.api.entities.product.ProductEntity>) {
        val searchResultList = mutableListOf<ProductEntity>()

        for (product in searchProductsResult) {
            searchResultList.add(product.createProduct())
        }

        viewModelScope.launch {
            _searchResultItems.emit(searchResultList)
        }
    }

    private fun handleCategoriesResult(searchCategoriesResult: List<CategoryEntity>?) {
        viewModelScope.launch {
            _searchResultCategoriesItems.emit(searchCategoriesResult?.toMutableList() ?: mutableListOf())
        }
    }
}

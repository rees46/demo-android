package rees46.demo_android.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.search.domain.models.Category
import rees46.demo_android.feature.search.domain.usecase.SearchProductsUseCase
import rees46.demo_android.feature.search.domain.usecase.SearchRecommendedProductsUseCase

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val searchRecommendedProductsUseCase: SearchRecommendedProductsUseCase
) : ViewModel() {

    private val _searchResultItems: MutableSharedFlow<MutableList<Product>> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val searchResultItems: Flow<MutableList<Product>> =
        _searchResultItems

    private val _searchResultCategoriesItems: MutableSharedFlow<MutableList<Category>> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val searchResultCategoriesItems: Flow<MutableList<Category>> =
        _searchResultCategoriesItems

    fun searchProduct(query: String = "") {
        if (query.isEmpty()) {
            emptySearch()
        }
        else {
            searchProductsUseCase.execute(
                query = query,
                onGetProducts = { handleProductResult(it) },
                onGetCategories = { handleCategoriesResult(it) }
            )
        }
    }

    private fun emptySearch() {
        searchRecommendedProductsUseCase.execute(
            onGetProducts = { handleProductResult(it) }
        )
    }

    private fun handleProductResult(searchProductsResult: List<Product>) {
        viewModelScope.launch {
            _searchResultItems.emit(searchProductsResult.toMutableList())
        }
    }

    private fun handleCategoriesResult(searchCategoriesResult: List<Category>?) {
        viewModelScope.launch {
            _searchResultCategoriesItems.emit(searchCategoriesResult?.toMutableList() ?: mutableListOf())
        }
    }
}

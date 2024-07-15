package rees46.demo_android.app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.domain.models.CategoryDto
import rees46.demo_android.domain.models.ProductDto
import rees46.demo_android.domain.features.search.usecase.SearchProductsUseCase
import rees46.demo_android.domain.features.search.usecase.SearchRecommendedProductsUseCase

class MainViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val searchRecommendedProductsUseCase: SearchRecommendedProductsUseCase
) : ViewModel() {

    private val _searchResultItems: MutableSharedFlow<MutableList<ProductDto>> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val searchResultItems: Flow<MutableList<ProductDto>> =
        _searchResultItems

    private val _searchResultCategoriesItems: MutableSharedFlow<MutableList<CategoryDto>> =
        MutableSharedFlow(extraBufferCapacity = 1)
    val searchResultCategoriesItems: Flow<MutableList<CategoryDto>> =
        _searchResultCategoriesItems

    fun searchProduct(query: String = "") {
        if (query.isEmpty()) {
            emptySearch()
        }
        else {
            searchProductsUseCase.invoke(
                query = query,
                onGetProducts = { handleProductResult(it) },
                onGetCategories = { handleCategoriesResult(it) }
            )
        }
    }

    private fun emptySearch() {
        searchRecommendedProductsUseCase.invoke(
            onGetProducts = { handleProductResult(it) }
        )
    }

    private fun handleProductResult(searchProductsResult: List<ProductDto>) {
        viewModelScope.launch {
            _searchResultItems.emit(searchProductsResult.toMutableList())
        }
    }

    private fun handleCategoriesResult(searchCategoriesResult: List<CategoryDto>?) {
        viewModelScope.launch {
            _searchResultCategoriesItems.emit(searchCategoriesResult?.toMutableList() ?: mutableListOf())
        }
    }
}

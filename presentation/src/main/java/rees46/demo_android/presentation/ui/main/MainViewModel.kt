package rees46.demo_android.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.domain.entities.CategoryEntity
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.usecase.search.SearchProductsUseCase
import rees46.demo_android.domain.usecase.search.SearchRecommendedProductsUseCase

class MainViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val searchRecommendedProductsUseCase: SearchRecommendedProductsUseCase
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

    private fun handleProductResult(searchProductsResult: List<ProductEntity>) {
        viewModelScope.launch {
            _searchResultItems.emit(searchProductsResult.toMutableList())
        }
    }

    private fun handleCategoriesResult(searchCategoriesResult: List<CategoryEntity>?) {
        viewModelScope.launch {
            _searchResultCategoriesItems.emit(searchCategoriesResult?.toMutableList() ?: mutableListOf())
        }
    }
}

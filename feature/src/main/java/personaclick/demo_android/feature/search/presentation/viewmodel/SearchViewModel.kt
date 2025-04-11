package personaclick.demo_android.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import personaclick.demo_android.feature.search.domain.models.Search
import personaclick.demo_android.feature.search.domain.usecase.SearchProductsUseCase
import personaclick.demo_android.feature.search.domain.usecase.SearchRecommendedProductsUseCase

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val searchRecommendedProductsUseCase: SearchRecommendedProductsUseCase
) : ViewModel() {

    private val _searchResultItems: MutableStateFlow<Search> = MutableStateFlow(Search(listOf(), listOf()))
    val searchResultItems: Flow<Search> = _searchResultItems

    fun searchProduct(query: String = "") {
        if (query.isEmpty()) {
            emptySearch()
        }
        else {
            searchProductsUseCase.invoke(
                query = query,
                onGetSearch = { handleSearchResult(it) }
            )
        }
    }

    private fun emptySearch() {
        searchRecommendedProductsUseCase.invoke(
            onGetSearch = {
                handleSearchResult(it)
            }
        )
    }

    private fun handleSearchResult(search: Search) {
        viewModelScope.launch {
            _searchResultItems.emit(search)
        }
    }
}

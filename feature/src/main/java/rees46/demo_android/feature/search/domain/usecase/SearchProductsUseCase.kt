package rees46.demo_android.feature.search.domain.usecase

import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.search.domain.repository.SearchRepository
import rees46.demo_android.feature.search.domain.models.Category

class SearchProductsUseCase (
    private val searchRepository: SearchRepository
) {

    fun invoke(
        query: String,
        onGetProducts: (List<Product>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    ) {
        searchRepository.searchProducts(query, onGetProducts, onGetCategories)
    }
}

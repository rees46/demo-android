package rees46.demo_android.feature.search.domain.usecase

import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.search.domain.repository.SearchRepository

class SearchRecommendedProductsUseCase (
    private val searchRepository: SearchRepository
) {

    fun execute(
        onGetProducts: (List<Product>) -> Unit
    ) {
        searchRepository.searchRecommendedProducts(onGetProducts)
    }
}

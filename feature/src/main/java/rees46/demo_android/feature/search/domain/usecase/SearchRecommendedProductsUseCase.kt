package rees46.demo_android.feature.search.domain.usecase

import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.search.domain.repository.SearchRepository

class SearchRecommendedProductsUseCase (
    private val searchRepository: SearchRepository
) {

    operator fun invoke(
        onGetProducts: (List<Product>) -> Unit
    ) {
        searchRepository.searchRecommendedProducts(onGetProducts)
    }
}

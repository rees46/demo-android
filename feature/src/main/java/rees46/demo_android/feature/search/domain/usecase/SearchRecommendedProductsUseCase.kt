package rees46.demo_android.feature.search.domain.usecase

import rees46.demo_android.feature.productDetails.domain.models.ProductDto
import rees46.demo_android.feature.productDetails.domain.repository.ProductRepository

class SearchRecommendedProductsUseCase (
    private val productRepository: ProductRepository
) {

    operator fun invoke(
        onGetProducts: (List<ProductDto>) -> Unit
    ) {
        productRepository.searchRecommendedProducts(onGetProducts)
    }
}

package rees46.demo_android.domain.features.search.usecase

import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.repository.ProductRepository

class SearchRecommendedProductsUseCase (
    private val productRepository: ProductRepository
) {

    operator fun invoke(
        onGetProducts: (List<ProductEntity>) -> Unit
    ) {
        productRepository.searchRecommendedProducts(onGetProducts)
    }
}

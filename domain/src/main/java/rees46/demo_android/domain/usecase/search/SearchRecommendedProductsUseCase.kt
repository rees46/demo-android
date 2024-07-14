package rees46.demo_android.domain.usecase.search

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

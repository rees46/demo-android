package rees46.demo_android.domain.usecase.search

import rees46.demo_android.domain.entities.CategoryEntity
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.repository.ProductRepository

class SearchProductsUseCase (
    private val productRepository: ProductRepository
) {

    operator fun invoke(
        query: String,
        onGetProducts: (List<ProductEntity>) -> Unit,
        onGetCategories: (List<CategoryEntity>) -> Unit
    ) {
        productRepository.searchProducts(query, onGetProducts, onGetCategories)
    }
}

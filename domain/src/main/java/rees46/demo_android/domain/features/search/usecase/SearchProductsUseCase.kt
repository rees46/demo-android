package rees46.demo_android.domain.features.search.usecase

import rees46.demo_android.domain.models.CategoryDto
import rees46.demo_android.domain.models.ProductDto
import rees46.demo_android.domain.repository.ProductRepository

class SearchProductsUseCase (
    private val productRepository: ProductRepository
) {

    operator fun invoke(
        query: String,
        onGetProducts: (List<ProductDto>) -> Unit,
        onGetCategories: (List<CategoryDto>) -> Unit
    ) {
        productRepository.searchProducts(query, onGetProducts, onGetCategories)
    }
}

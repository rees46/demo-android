package rees46.demo_android.domain.usecase.search

import com.personalizatio.api.responses.search.Category
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.repository.ProductRepository

class SearchProductsUseCase (
    private val productRepository: ProductRepository
) {

    operator fun invoke(
        query: String,
        onGetProducts: (List<ProductEntity>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    ) {
        productRepository.searchProducts(query, onGetProducts, onGetCategories)
    }
}

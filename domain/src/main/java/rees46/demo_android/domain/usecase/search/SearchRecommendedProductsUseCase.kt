package rees46.demo_android.domain.usecase.search

import com.personalizatio.api.responses.product.Product
import rees46.demo_android.domain.repository.ProductRepository

class SearchRecommendedProductsUseCase (
    private val productRepository: ProductRepository
) {

    operator fun invoke(
        onGetProducts: (List<Product>) -> Unit
    ) {
        productRepository.searchRecommendedProducts(onGetProducts)
    }
}

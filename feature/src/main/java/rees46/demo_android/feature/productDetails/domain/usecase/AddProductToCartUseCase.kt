package rees46.demo_android.feature.productDetails.domain.usecase

import rees46.demo_android.feature.productDetails.domain.models.ProductDto
import rees46.demo_android.feature.cart.domain.repository.CartRepository

class AddProductToCartUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(product: ProductDto, quantity: Int) {
        cartRepository.addProduct(product, quantity)
    }
}

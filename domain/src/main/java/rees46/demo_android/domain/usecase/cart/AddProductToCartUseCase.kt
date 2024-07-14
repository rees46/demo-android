package rees46.demo_android.domain.usecase.cart

import rees46.demo_android.domain.entities.ProductDto
import rees46.demo_android.domain.repository.CartRepository

class AddProductToCartUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(product: ProductDto, quantity: Int) {
        cartRepository.addProduct(product, quantity)
    }
}

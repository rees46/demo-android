package rees46.demo_android.domain.features.cart.usecase

import rees46.demo_android.domain.models.CartProductDto
import rees46.demo_android.domain.repository.CartRepository

class GetCartProductsUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(): MutableList<CartProductDto> =
        cartRepository.getCartProducts()
}

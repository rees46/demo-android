package rees46.demo_android.domain.usecase.cart

import rees46.demo_android.domain.entities.CartProductDto
import rees46.demo_android.domain.repository.CartRepository

class GetCartProductsUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(): MutableList<CartProductDto> =
        cartRepository.getCartProducts()
}

package rees46.demo_android.feature.cart.domain.usecase

import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.cart.domain.repository.CartRepository

class GetCartProductsUseCase (
    private val cartRepository: CartRepository
) {

    fun execute(): MutableList<CartProduct> =
        cartRepository.getCartProducts()
}

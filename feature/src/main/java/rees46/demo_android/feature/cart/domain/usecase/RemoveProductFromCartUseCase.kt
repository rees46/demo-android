package rees46.demo_android.feature.cart.domain.usecase

import rees46.demo_android.feature.cart.domain.repository.CartRepository

class RemoveProductFromCartUseCase (
    private val cartRepository: CartRepository
) {

    fun invoke(productId: String) {
        cartRepository.removeProduct(productId)
    }
}

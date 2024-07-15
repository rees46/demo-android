package rees46.demo_android.domain.features.cart.usecase

import rees46.demo_android.domain.repository.CartRepository

class RemoveProductFromCartUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(productId: String) {
        cartRepository.removeProduct(productId)
    }
}

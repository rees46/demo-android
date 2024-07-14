package rees46.demo_android.domain.usecase.cart

import rees46.demo_android.domain.entities.CartProductEntity
import rees46.demo_android.domain.repository.CartRepository

class GetCartProductUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(productId: String): CartProductEntity? =
        cartRepository.getCartProduct(productId)
}

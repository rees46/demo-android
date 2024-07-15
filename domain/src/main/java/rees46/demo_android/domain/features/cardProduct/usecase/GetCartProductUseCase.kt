package rees46.demo_android.domain.features.cardProduct.usecase

import rees46.demo_android.domain.models.CartProductDto
import rees46.demo_android.domain.repository.CartRepository

class GetCartProductUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(productId: String): CartProductDto? =
        cartRepository.getCartProduct(productId)
}

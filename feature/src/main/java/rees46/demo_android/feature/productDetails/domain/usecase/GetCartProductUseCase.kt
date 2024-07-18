package rees46.demo_android.feature.productDetails.domain.usecase

import rees46.demo_android.feature.productDetails.domain.models.CartProductDto
import rees46.demo_android.feature.cart.domain.repository.CartRepository

class GetCartProductUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(productId: String): CartProductDto? =
        cartRepository.getCartProduct(productId)
}

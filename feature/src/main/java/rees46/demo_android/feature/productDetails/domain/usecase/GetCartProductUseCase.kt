package rees46.demo_android.feature.productDetails.domain.usecase

import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.cart.domain.repository.CartRepository

class GetCartProductUseCase (
    private val cartRepository: CartRepository
) {

    fun execute(
        productId: String
    ): CartProduct? =
        cartRepository.getCartProduct(productId)
}

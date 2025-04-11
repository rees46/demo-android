package personaclick.demo_android.feature.productDetails.domain.usecase

import personaclick.demo_android.feature.cart.domain.models.CartProduct
import personaclick.demo_android.feature.cart.domain.repository.CartRepository

class GetCartProductUseCase (
    private val cartRepository: CartRepository
) {

    fun invoke(
        productId: String
    ): CartProduct? =
        cartRepository.getCartProduct(productId)
}

package personaclick.demo_android.feature.productDetails.domain.usecase

import personaclick.demo_android.feature.cart.domain.repository.CartRepository
import personaclick.demo_android.feature.productDetails.domain.models.Product

class AddProductToCartUseCase (
    private val cartRepository: CartRepository
) {

    fun invoke(
        product: Product,
        quantity: Int
    ) {
        cartRepository.addProduct(
            product = product,
            quantity = quantity
        )
    }
}

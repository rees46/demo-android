package rees46.demo_android.feature.cart.data.repository

import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.MutableStateFlow
import rees46.demo_android.feature.cart.data.models.Cart
import rees46.demo_android.feature.cart.domain.repository.CartRepository
import rees46.demo_android.feature.productDetails.domain.models.CartProductDto
import rees46.demo_android.feature.productDetails.domain.models.ProductDto

class CartRepositoryImpl (
    private val sdk: SDK,
    private val cart: Cart
) : CartRepository {

    override fun getCartProducts(): MutableList<CartProductDto> =
        cart.cartProducts

    override fun getCartProduct(productId: String): CartProductDto? =
        cart.getCartProduct(productId)

    override fun addProduct(product: ProductDto, quantity: Int) {
        sdk.trackEventManager.track(
            event = Params.TrackEvent.VIEW,
            productId = product.id
        )

        cart.addProduct(
            product = product,
            quantity = quantity
        )
    }

    override fun removeProduct(productId: String) {
        sdk.trackEventManager.track(
            event = Params.TrackEvent.REMOVE_FROM_CART,
            params = Params(),
            listener = null
        )

        cart.removeProduct(productId)
    }

    override fun getSumPrice(): MutableStateFlow<Double> =
        cart.cartSumPrice
}

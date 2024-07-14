package rees46.demo_android.data.repository.cart

import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.MutableStateFlow
import rees46.demo_android.domain.entities.CartProductEntity
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.repository.CartRepository

class CartRepositoryImpl (
    private val sdk: SDK,
    private val cart: Cart
) : CartRepository {

    override fun getCartProducts(): MutableList<CartProductEntity> =
        cart.cartProducts

    override fun getCartProduct(productId: String): CartProductEntity? =
        cart.getCartProduct(productId)

    override fun addProduct(product: ProductEntity, quantity: Int) {
        sdk.trackEventManager.track(Params.TrackEvent.VIEW, product.id)

        cart.addProduct(product, quantity)
    }

    override fun removeProduct(productId: String) {
        sdk.trackEventManager.track(Params.TrackEvent.REMOVE_FROM_CART, Params(), null)

        cart.removeProduct(productId)
    }

    override fun getSumPrice(): MutableStateFlow<Double> =
        cart.cartSumPrice
}

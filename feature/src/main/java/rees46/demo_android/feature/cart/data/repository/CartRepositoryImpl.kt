package rees46.demo_android.feature.cart.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import rees46.demo_android.feature.cart.data.api.CartApi
import rees46.demo_android.feature.cart.data.models.Cart
import rees46.demo_android.feature.cart.domain.repository.CartRepository
import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.productDetails.domain.models.Product

class CartRepositoryImpl (
    private val cartApi: CartApi,
    private val cart: Cart
) : CartRepository {

    override fun getCartProducts(): MutableList<CartProduct> =
        cart.cartProducts

    override fun getCartProduct(productId: String): CartProduct? =
        cart.getCartProduct(productId)

    override fun addProduct(product: Product, quantity: Int) {
        cartApi.addProduct(
            product = product,
            quantity = quantity
        )

        cart.addProduct(
            product = product,
            quantity = quantity
        )
    }

    override fun removeProduct(productId: String) {
        cartApi.removeProduct(productId)

        cart.removeProduct(productId)
    }

    override fun getSumPrice(): MutableStateFlow<Double> =
        cart.cartSumPrice
}

package rees46.demo_android.feature.cart.domain.repository

import kotlinx.coroutines.flow.StateFlow
import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.productDetails.domain.models.Product

interface CartRepository {

    fun getCartProducts() : StateFlow<MutableList<CartProduct>>
    fun getCartProduct(productId: String): CartProduct?

    fun addProduct(product: Product, quantity: Int)
    fun removeProduct(productId: String)

    fun getSumPrice(): StateFlow<Double>
}

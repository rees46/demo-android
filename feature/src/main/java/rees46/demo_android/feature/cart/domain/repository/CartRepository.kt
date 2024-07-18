package rees46.demo_android.feature.cart.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.productDetails.domain.models.Product

interface CartRepository {

    fun getCartProducts() : MutableList<CartProduct>
    fun getCartProduct(productId: String): CartProduct?

    fun addProduct(product: Product, quantity: Int)
    fun removeProduct(productId: String)

    fun getSumPrice(): MutableStateFlow<Double>
}

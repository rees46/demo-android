package rees46.demo_android.feature.cart.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import rees46.demo_android.feature.product.domain.models.CartProductDto
import rees46.demo_android.feature.product.domain.models.ProductDto

interface CartRepository {

    fun getCartProducts() : MutableList<CartProductDto>
    fun getCartProduct(productId: String): CartProductDto?

    fun addProduct(product: ProductDto, quantity: Int)
    fun removeProduct(productId: String)

    fun getSumPrice(): MutableStateFlow<Double>
}

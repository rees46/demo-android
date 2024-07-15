package rees46.demo_android.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import rees46.demo_android.domain.models.CartProductDto
import rees46.demo_android.domain.models.ProductDto

interface CartRepository {

    fun getCartProducts() : MutableList<CartProductDto>
    fun getCartProduct(productId: String): CartProductDto?

    fun addProduct(product: ProductDto, quantity: Int)
    fun removeProduct(productId: String)

    fun getSumPrice(): MutableStateFlow<Double>
}

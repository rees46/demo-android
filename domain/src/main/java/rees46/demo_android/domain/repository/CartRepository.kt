package rees46.demo_android.domain.repository

import kotlinx.coroutines.flow.MutableStateFlow
import rees46.demo_android.domain.entities.CartProductEntity
import rees46.demo_android.domain.entities.ProductEntity

interface CartRepository {

    fun getCartProducts() : MutableList<CartProductEntity>
    fun getCartProduct(productId: String): CartProductEntity?

    fun addProduct(product: ProductEntity, quantity: Int)
    fun removeProduct(productId: String)

    fun getSumPrice(): MutableStateFlow<Double>
}

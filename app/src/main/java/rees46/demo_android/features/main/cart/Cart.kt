package rees46.demo_android.features.main.cart

import rees46.demo_android.entity.productsEntity.CartProductEntity
import rees46.demo_android.entity.productsEntity.ProductEntity

// TODO: removed after implementation getting cart in sdk
object Cart {

    val cartProducts: ArrayList<CartProductEntity> = arrayListOf()

    fun getCartProduct(productId: String) : CartProductEntity? {
        return cartProducts.find { product -> product.product.id == productId }
    }

    fun addProduct(product: ProductEntity, quantity: Int) {
        val cartProduct = getCartProduct(product.id)
        if(cartProduct == null) {
            cartProducts.add(CartProductEntity(product, quantity))
        }
        else {
            cartProduct.quantity += quantity
        }
    }

    fun removeProduct(productId: String) {
        cartProducts.removeIf { product -> product.product.id == productId }
    }
}
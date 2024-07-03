package rees46.demo_android.features.main.cart

import rees46.demo_android.features.product.Product

// TODO: removed after implementation getting cart in sdk
object Cart {

    val cartProducts: ArrayList<CartProduct> = arrayListOf()

    fun getCartProduct(productId: String) : CartProduct? {
        return cartProducts.find { product -> product.product.id == productId }
    }

    fun addProduct(product: Product, quantity: Int) {
        val cartProduct = getCartProduct(product.id)
        if(cartProduct == null) {
            cartProducts.add(CartProduct(product, quantity))
        }
        else {
            cartProduct.quantity += quantity
        }
    }

    fun removeProduct(productId: String) {
        cartProducts.removeIf { product -> product.product.id == productId }
    }
}
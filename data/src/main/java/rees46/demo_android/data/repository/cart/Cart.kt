package rees46.demo_android.data.repository.cart

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import rees46.demo_android.domain.entities.CartProductEntity
import rees46.demo_android.domain.entities.ProductEntity

// TODO: removed after implementation getting cart in sdk
class Cart {

    val cartProducts: ArrayList<CartProductEntity> = arrayListOf()

    var cartSumPrice: MutableStateFlow<Double> = MutableStateFlow(0.0)

    fun getCartProduct(productId: String) : CartProductEntity? {
        return cartProducts.find { product -> product.product.id == productId }
    }

    fun addProduct(product: ProductEntity, quantity: Int) {
        cartSumPrice.update { currentSum ->
            val result: Double = currentSum + (product.price ?: 0.0)
            result
        }

        val cartProduct = getCartProduct(product.id)
        if(cartProduct == null) {
            cartProducts.add(CartProductEntity(product, quantity))
        }
        else {
            cartProduct.quantity += quantity
        }
    }

    fun removeProduct(productId: String) {
        cartSumPrice.update { currentSum ->
            val quantity = getCartProduct(productId)?.quantity ?: 0

            getCartProduct(productId)?.product?.price?.let {
                currentSum - (it * quantity)
            } ?: currentSum
        }

        cartProducts.removeIf { product -> product.product.id == productId }
    }
}

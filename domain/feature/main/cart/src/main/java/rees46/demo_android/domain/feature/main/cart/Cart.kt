package rees46.demo_android.domain.feature.main.cart

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import rees46.demo_android.data.products.CartProductDto
import rees46.demo_android.data.products.ProductDto

// TODO: removed after implementation getting cart in sdk
object Cart {

    val cartProducts: ArrayList<CartProductDto> = arrayListOf()

    var cartSumPrices: MutableStateFlow<Double> = MutableStateFlow(0.0)

    fun getCartProduct(productId: String) : CartProductDto? {
        return cartProducts.find { product -> product.product.id == productId }
    }

    fun addProduct(product: ProductDto, quantity: Int) {

        cartSumPrices.update { currentSum ->
            val result: Double = currentSum + (product.price ?: 0.0)
            result
        }
        val cartProduct = getCartProduct(product.id)
        if(cartProduct == null) {
            cartProducts.add(
                CartProductDto(
                    product,
                    quantity
                )
            )
        }
        else {
            cartProduct.quantity += quantity
        }
    }

    fun removeProduct(productId: String) {

        cartSumPrices.update { currentSum ->
            val quantity = getCartProduct(productId)?.quantity ?: 0

            getCartProduct(productId)?.product?.price?.let {
                currentSum - (it * quantity)
            } ?: currentSum
        }
        cartProducts.removeIf { product -> product.product.id == productId }
    }
}
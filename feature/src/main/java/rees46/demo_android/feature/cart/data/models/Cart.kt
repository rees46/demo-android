package rees46.demo_android.feature.cart.data.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import rees46.demo_android.feature.product.domain.models.CartProductDto
import rees46.demo_android.feature.product.domain.models.ProductDto

// TODO: removed after implementation getting cart in sdk
class Cart {

    val cartProducts: ArrayList<CartProductDto> = arrayListOf()

    var cartSumPrice: MutableStateFlow<Double> = MutableStateFlow(0.0)

    fun getCartProduct(productId: String) : CartProductDto? {
        return cartProducts.find { product -> product.product.id == productId }
    }

    fun addProduct(product: ProductDto, quantity: Int) {
        cartSumPrice.update { currentSum ->
            val result: Double = currentSum + (product.price ?: 0.0)
            result
        }

        val cartProduct = getCartProduct(product.id)
        if(cartProduct == null) {
            cartProducts.add(
                CartProductDto(
                    product = product,
                    quantity = quantity
                )
            )
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

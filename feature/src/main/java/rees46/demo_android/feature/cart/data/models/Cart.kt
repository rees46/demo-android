package rees46.demo_android.feature.cart.data.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.productDetails.domain.models.Product

// TODO: removed after implementation getting cart in sdk
class Cart {

    val cartProductsFlow: MutableStateFlow<MutableList<CartProduct>> =
        MutableStateFlow(mutableListOf())

    var cartSumPrice: MutableStateFlow<Double> = MutableStateFlow(0.0)

    fun getCartProduct(productId: String): CartProduct? {
        return cartProductsFlow.value.find { product ->
            product.product.id == productId
        }
    }

    fun addProduct(
        product: Product,
        quantity: Int
    ) {
        cartSumPrice.update { currentSum ->
            val result: Double = currentSum + (product.price ?: 0.0)
            result
        }

        val cartProduct = getCartProduct(product.id)
        if (cartProduct == null) {
            cartProductsFlow.update {
                cartProductsFlow.value.toMutableList().apply {
                    this.add(
                        CartProduct(
                            product = product,
                            quantity = quantity
                        )
                    )
                }
            }
        } else {
            cartProduct.quantity += quantity
        }
    }

    fun removeProduct(productId: String) {
        cartSumPrice.update { currentSum ->
            val cartProduct = getCartProduct(productId)

            val quantity = cartProduct?.quantity ?: 0

            cartProduct?.product?.price?.let {
                currentSum - (it * quantity)
            } ?: currentSum
        }

        cartProductsFlow.update {
            cartProductsFlow.value.toMutableList().apply {
                this.removeIf { product ->
                    product.product.id == productId
                }

            }
        }
    }
}

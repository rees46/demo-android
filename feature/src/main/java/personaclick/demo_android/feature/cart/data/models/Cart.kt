package personaclick.demo_android.feature.cart.data.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import personaclick.demo_android.feature.cart.domain.models.CartProduct
import personaclick.demo_android.feature.productDetails.domain.models.Product

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
        val cartProduct = getCartProduct(product.id)
        if (cartProduct == null) {
            cartProductsFlow.update {
                it.toMutableList().apply {
                    add(
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

        updateCartSumPrice()
    }

    fun removeProduct(productId: String) {
        cartProductsFlow.update {
            it.toMutableList().apply {
                removeIf { product ->
                    product.product.id == productId
                }
            }
        }

        updateCartSumPrice()
    }

    private fun updateCartSumPrice() {
        cartSumPrice.update {
            cartProductsFlow.value.sumOf { cartProduct ->
                getCartProductPrice(cartProduct)
            }
        }
    }

    private fun getCartProductPrice(cartProduct: CartProduct) =
        (cartProduct.product.price ?: 0.0) * cartProduct.quantity
}

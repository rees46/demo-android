package rees46.demo_android.features.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import com.personalizatio.api.OnApiCallbackListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

class CartViewModel(
    private val sdk: SDK
) : ViewModel() {

    private val _cartProductsFlow: MutableSharedFlow<ArrayList<CartProduct>> = MutableSharedFlow()
    val cartProductsFlow: Flow<List<CartProduct>> = _cartProductsFlow
    private var oldCartProducts = arrayListOf<CartProduct>()
    private val cartProducts = arrayListOf<CartProduct>()

    internal fun updateCart() {
        oldCartProducts = ArrayList(cartProducts)

        cartProducts.clear()

        sdk.cartManager.getCart(
            onGetCart = { cartEntity ->
                for (item in cartEntity.data.items) {
                    val cartProduct = oldCartProducts.find { cartProduct -> cartProduct.product.id == item.uniqid }

                    if (cartProduct != null) {
                        cartProducts.add(cartProduct)
                        continue
                    }

                    //TODO: implemented getting product info
                    //cartProducts.add(CartProduct(product, item.quantity))
                    //updateCartProducts()
                }

                updateCartProducts()
            }
        )
    }

    internal fun removeProduct(cartProduct: CartProduct) {
        sdk.cartManager.removeFromCart(cartProduct.product.id, cartProduct.quantity, object : OnApiCallbackListener() {
            override fun onSuccess(response: JSONObject?) {
                updateCart()
            }
        })
    }

    private fun updateCartProducts() {
        viewModelScope.launch {
            _cartProductsFlow.emit(cartProducts)
        }
    }
}

package rees46.demo_android.features.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val sdk: SDK
) : ViewModel() {

    private val _cartProductsFlow: MutableSharedFlow<ArrayList<CartProduct>> = MutableSharedFlow()
    val cartProductsFlow: Flow<List<CartProduct>> = _cartProductsFlow
    private val cartProducts = arrayListOf<CartProduct>()

    internal fun updateCarts() {
        cartProducts.clear()

        cartProducts.addAll(Cart.cartProducts)

        updateCartProducts()
    }

    internal fun removeProduct(cartProduct: CartProduct) {
        sdk.trackEventManager.track(Params.TrackEvent.REMOVE_FROM_CART, Params(), null)

        Cart.removeProduct(cartProduct.product.id)
        cartProducts.remove(cartProduct)
        updateCartProducts()
    }

    private fun updateCartProducts() {
        viewModelScope.launch {
            _cartProductsFlow.emit(cartProducts)
        }
    }
}

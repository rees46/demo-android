package rees46.demo_android.features.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.entities.products.CartProductEntity
import rees46.demo_android.entities.products.ProductEntity
import rees46.demo_android.features.recommendationBlock.RecommendationUtils

class CartViewModel(
    private val sdk: SDK
) : ViewModel() {

    private val _cartProductsFlow: MutableSharedFlow<ArrayList<CartProductEntity>> =
        MutableSharedFlow()
    val cartProductsFlow: Flow<MutableList<CartProductEntity>> = _cartProductsFlow
    private val cartProducts = arrayListOf<CartProductEntity>()

    private val _recommendationProductsFlow: MutableSharedFlow<MutableList<ProductEntity>> =
        MutableSharedFlow()
    val recommendationProductsFlow: Flow<MutableList<ProductEntity>> = _recommendationProductsFlow
    val pricesFlow: Flow<Double> = Cart.cartSumPrices

    init {
        RecommendationUtils.updateExtendedRecommendation(sdk, RECOMMENDER_CODE) {
            viewModelScope.launch { _recommendationProductsFlow.emit(it) }
        }
    }

    internal fun updateCarts() {
        cartProducts.clear()
        cartProducts.addAll(Cart.cartProducts)

        updateCartProducts()
    }

    internal fun removeProduct(cartProduct: CartProductEntity) {
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

    companion object {
        var RECOMMENDER_CODE = "2dbebc39bee259b118bcc0ac3fa74a42"
    }
}

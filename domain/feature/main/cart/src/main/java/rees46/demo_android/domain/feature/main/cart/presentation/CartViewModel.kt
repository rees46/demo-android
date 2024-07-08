package rees46.demo_android.domain.feature.main.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.domain.feature.main.cart.Cart
import rees46.demo_android.data.products.CartProductDto
import rees46.demo_android.data.products.ProductDto

class CartViewModel(
    private val sdk: SDK
) : ViewModel() {

    private val _cartProductsFlow: MutableSharedFlow<ArrayList<CartProductDto>> =
        MutableSharedFlow()
    val cartProductsFlow: Flow<MutableList<CartProductDto>> = _cartProductsFlow
    private val cartProducts = arrayListOf<CartProductDto>()

    private val _recommendationProductsFlow: MutableSharedFlow<MutableList<ProductDto>> =
        MutableSharedFlow()
    val recommendationProductsFlow: Flow<MutableList<ProductDto>> = _recommendationProductsFlow
    val pricesFlow: Flow<Double> = Cart.cartSumPrices

    init {
        rees46.demo_android.domain.feature.recommendation_block.utils.RecommendationUtils.updateExtendedRecommendation(sdk, RECOMMENDER_CODE) {
            viewModelScope.launch { _recommendationProductsFlow.emit(it) }
        }
    }

    fun updateCarts() {
        cartProducts.clear()
        cartProducts.addAll(Cart.cartProducts)

        updateCartProducts()
    }

    fun removeProduct(cartProduct: CartProductDto) {
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

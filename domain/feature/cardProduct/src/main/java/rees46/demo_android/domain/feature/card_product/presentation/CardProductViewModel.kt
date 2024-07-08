package rees46.demo_android.domain.feature.card_product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rees46.demo_android.data.products.ProductDto
import rees46.demo_android.domain.feature.card_product.CardAction

class CardProductViewModel(
    private val sdk: SDK,
    product: ProductDto
) : ViewModel() {

    private val _recommendedProductsFlow: MutableSharedFlow<MutableList<ProductDto>> =
        MutableSharedFlow()
    val recommendedProductsFlow: Flow<MutableList<ProductDto>> = _recommendedProductsFlow

    private val _currentProductFlow: MutableStateFlow<ProductDto> =
        MutableStateFlow(product)
    val currentProductFlow: Flow<ProductDto> = _currentProductFlow

    private var _countCartProductFlow: MutableStateFlow<Int> =
        MutableStateFlow(rees46.demo_android.domain.feature.main.cart.Cart.getCartProduct(product.id)?.quantity ?: 1)
    var countCartProductFlow: Flow<Int> = _countCartProductFlow

    fun updateProduct(product: ProductDto) {
        _countCartProductFlow.update { rees46.demo_android.domain.feature.main.cart.Cart.getCartProduct(product.id)?.quantity ?: 1 }
        _currentProductFlow.update { product }
    }

    fun updateRecommendationBlock(productId: String) {
        rees46.demo_android.domain.feature.recommendation_block.utils.RecommendationUtils.updateExtendedRecommendationForProduct(sdk, RECOMMENDER_CODE, productId) {
            viewModelScope.launch { _recommendedProductsFlow.emit(it) }
        }
    }

    fun proceedCartAction(action: CardAction) {
        when (action) {
            CardAction.ADD -> addToCart()
            CardAction.INCREASE -> increaseCount()
            CardAction.DECREASE -> decreaseCount()
        }
    }

    private fun addToCart() {
        rees46.demo_android.domain.feature.main.cart.Cart.addProduct(_currentProductFlow.value, _countCartProductFlow.value)
        sdk.trackEventManager.track(Params.TrackEvent.VIEW, _currentProductFlow.value.id)
    }

    private fun increaseCount() = _countCartProductFlow.update { it.inc() }

    private fun decreaseCount() {
        if (_countCartProductFlow.value > 1) {
            _countCartProductFlow.update { it.dec() }
        }
    }

    companion object {
        var RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

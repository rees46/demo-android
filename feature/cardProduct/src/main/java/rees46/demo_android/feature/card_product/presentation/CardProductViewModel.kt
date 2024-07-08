package rees46.demo_android.feature.card_product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rees46.demo_android.entities.products.ProductEntity
import rees46.demo_android.feature.card_product.CardAction
import rees46.demo_android.feature.main.cart.Cart
import rees46.demo_android.feature.recommendation_block.utils.RecommendationUtils

class CardProductViewModel(
    private val sdk: SDK,
    product: ProductEntity
) : ViewModel() {

    private val _recommendedProductsFlow: MutableSharedFlow<MutableList<ProductEntity>> =
        MutableSharedFlow()
    val recommendedProductsFlow: Flow<MutableList<ProductEntity>> = _recommendedProductsFlow

    private val _currentProductFlow: MutableStateFlow<ProductEntity> =
        MutableStateFlow(product)
    val currentProductFlow: Flow<ProductEntity> = _currentProductFlow

    private var _countCartProductFlow: MutableStateFlow<Int> =
        MutableStateFlow(Cart.getCartProduct(product.id)?.quantity ?: 1)
    var countCartProductFlow: Flow<Int> = _countCartProductFlow

    fun updateProduct(product: ProductEntity) {
        _countCartProductFlow.update { Cart.getCartProduct(product.id)?.quantity ?: 1 }
        _currentProductFlow.update { product }
    }

    fun updateRecommendationBlock(productId: String) {
        RecommendationUtils.updateExtendedRecommendationForProduct(sdk, RECOMMENDER_CODE, productId) {
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
        Cart.addProduct(_currentProductFlow.value, _countCartProductFlow.value)
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
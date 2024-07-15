package rees46.demo_android.app.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.domain.entities.CartProductEntity
import rees46.demo_android.domain.entities.RecommendationEntity
import rees46.demo_android.domain.features.cart.usecase.GetCartProductsUseCase
import rees46.demo_android.domain.features.cart.usecase.GetCartSumPriceUseCase
import rees46.demo_android.domain.features.cart.usecase.RemoveProductFromCartUseCase
import rees46.demo_android.domain.features.recommendation.usecase.GetRecommendationUseCase

class CartViewModel(
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    getCartSumPriceUseCase: GetCartSumPriceUseCase,
    getRecommendationUseCase: GetRecommendationUseCase
) : ViewModel() {

    private val _cartProductsFlow: MutableSharedFlow<MutableList<CartProductEntity>> = MutableSharedFlow()
    val cartProductsFlow: Flow<MutableList<CartProductEntity>> = _cartProductsFlow

    private val _recommendationFlow: MutableSharedFlow<RecommendationEntity> = MutableSharedFlow()
    val recommendationFlow: Flow<RecommendationEntity> = _recommendationFlow

    val sumPriceFlow: Flow<Double> = getCartSumPriceUseCase.invoke()

    init {
        getRecommendationUseCase(RECOMMENDER_CODE) {
            viewModelScope.launch { _recommendationFlow.emit(it) }
        }
    }

    fun updateCarts() {
        updateCartProducts()
    }

    fun removeProduct(cartProduct: CartProductEntity) {
        removeProductFromCartUseCase.invoke(cartProduct.product.id)

        updateCartProducts()
    }

    private fun updateCartProducts() {
        viewModelScope.launch {
            _cartProductsFlow.emit(getCartProductsUseCase.invoke())
        }
    }

    companion object {
        var RECOMMENDER_CODE = "2dbebc39bee259b118bcc0ac3fa74a42"
    }
}

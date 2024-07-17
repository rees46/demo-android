package rees46.demo_android.domain.features.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.domain.models.CartProductDto
import rees46.demo_android.domain.models.RecommendationDto
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

    private val _cartProductsFlow: MutableSharedFlow<MutableList<CartProductDto>> = MutableSharedFlow()
    val cartProductsFlow: Flow<MutableList<CartProductDto>> = _cartProductsFlow

    private val _recommendationFlow: MutableSharedFlow<RecommendationDto> = MutableSharedFlow()
    val recommendationFlow: Flow<RecommendationDto> = _recommendationFlow

    val sumPriceFlow: Flow<Double> = getCartSumPriceUseCase.invoke()

    init {
        getRecommendationUseCase(RECOMMENDER_CODE) {
            viewModelScope.launch { _recommendationFlow.emit(it) }
        }
    }

    fun updateCarts() {
        updateCartProducts()
    }

    fun removeProduct(cartProduct: CartProductDto) {
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

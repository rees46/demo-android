package rees46.demo_android.feature.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rees46.demo_android.core.settings.RecommendationSettings
import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.cart.domain.usecase.GetCartProductsUseCase
import rees46.demo_android.feature.cart.domain.usecase.GetCartSumPriceUseCase
import rees46.demo_android.feature.cart.domain.usecase.RemoveProductFromCartUseCase
import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation
import rees46.demo_android.feature.recommendationBlock.domain.usecase.GetRecommendationUseCase

class CartViewModel(
    getCartProductsUseCase: GetCartProductsUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    getCartSumPriceUseCase: GetCartSumPriceUseCase,
    getRecommendationUseCase: GetRecommendationUseCase
) : ViewModel() {

    val cartProductsFlow: StateFlow<MutableList<CartProduct>> = getCartProductsUseCase.invoke()

    private val _recommendationFlow = MutableStateFlow(Recommendation("", emptyList()))
    val recommendationFlow: StateFlow<Recommendation> = _recommendationFlow.asStateFlow()

    val sumPriceFlow: StateFlow<Double> = getCartSumPriceUseCase.invoke()

    init {
        getRecommendationUseCase.invoke(
            recommenderCode = RecommendationSettings.CART_RECOMMENDED_CODE,
            onGetRecommendation = {
                viewModelScope.launch { _recommendationFlow.emit(it) }
            }
        )
    }

    fun removeProduct(cartProduct: CartProduct) {
        removeProductFromCartUseCase.invoke(cartProduct.product.id)
    }
}

package rees46.demo_android.feature.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    val cartProductsFlow: StateFlow<MutableList<CartProduct>> = getCartProductsUseCase.execute()

    private val _recommendationFlow = MutableStateFlow(Recommendation("", emptyList()))
    val recommendationFlow: StateFlow<Recommendation> = _recommendationFlow.asStateFlow()

    val sumPriceFlow: StateFlow<Double> = getCartSumPriceUseCase.execute()

    init {
        getRecommendationUseCase.execute(
            recommenderCode = RECOMMENDER_CODE,
            onGetRecommendation = {
                viewModelScope.launch { _recommendationFlow.emit(it) }
            }
        )
    }

    fun removeProduct(cartProduct: CartProduct) {
        removeProductFromCartUseCase.execute(cartProduct.product.id)
    }

    companion object {
        var RECOMMENDER_CODE = "2dbebc39bee259b118bcc0ac3fa74a42"
    }
}

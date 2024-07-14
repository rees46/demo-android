package rees46.demo_android.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.domain.entities.CartProductDto
import rees46.demo_android.domain.entities.ProductDto
import rees46.demo_android.domain.feature.recommendation_block.utils.RecommendationUtils
import rees46.demo_android.domain.usecase.cart.GetCartProductsUseCase
import rees46.demo_android.domain.usecase.cart.GetCartSumPriceUseCase
import rees46.demo_android.domain.usecase.cart.RemoveProductFromCartUseCase

class CartViewModel(
    sdk: SDK,
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    getCartSumPriceUseCase: GetCartSumPriceUseCase
) : ViewModel() {

    private val _cartProductsFlow: MutableSharedFlow<MutableList<CartProductDto>> = MutableSharedFlow()
    val cartProductsFlow: Flow<MutableList<CartProductDto>> = _cartProductsFlow

    private val _recommendationProductsFlow: MutableSharedFlow<MutableList<ProductDto>> =
        MutableSharedFlow()
    val recommendationProductsFlow: Flow<MutableList<ProductDto>> = _recommendationProductsFlow

    val sumPriceFlow: Flow<Double> = getCartSumPriceUseCase.invoke()

    init {
        RecommendationUtils.updateExtendedRecommendation(sdk, RECOMMENDER_CODE) {
            viewModelScope.launch { _recommendationProductsFlow.emit(it) }
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

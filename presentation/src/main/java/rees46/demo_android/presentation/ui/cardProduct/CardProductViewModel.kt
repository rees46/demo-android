package rees46.demo_android.presentation.ui.cardProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rees46.demo_android.domain.entities.ProductDto
import rees46.demo_android.domain.usecase.cart.AddProductToCartUseCase
import rees46.demo_android.domain.usecase.cart.GetCartProductUseCase
import rees46.demo_android.domain.usecase.recommendation.GetRecommendationForProductUseCase

class CardProductViewModel(
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getCartProductUseCase: GetCartProductUseCase,
    private val getRecommendationForProductUseCase: GetRecommendationForProductUseCase,
    product: ProductDto
) : ViewModel() {

    private val _recommendedProductsFlow: MutableSharedFlow<MutableList<ProductDto>> =
        MutableSharedFlow()
    val recommendedProductsFlow: Flow<MutableList<ProductDto>> = _recommendedProductsFlow

    private val _currentProductFlow: MutableStateFlow<ProductDto> =
        MutableStateFlow(product)
    val currentProductFlow: Flow<ProductDto> = _currentProductFlow

    private var _countCartProductFlow: MutableStateFlow<Int> =
        MutableStateFlow(getCartProductUseCase(product.id)?.quantity ?: 1)
    var countCartProductFlow: Flow<Int> = _countCartProductFlow

    fun updateProduct(product: ProductDto) {
        _countCartProductFlow.update { getCartProductUseCase(product.id)?.quantity ?: 1 }
        _currentProductFlow.update { product }
    }

    fun updateRecommendationBlock(productId: String) {
        getRecommendationForProductUseCase(RECOMMENDER_CODE, productId) {
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
        addProductToCartUseCase.invoke(_currentProductFlow.value, _countCartProductFlow.value)
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

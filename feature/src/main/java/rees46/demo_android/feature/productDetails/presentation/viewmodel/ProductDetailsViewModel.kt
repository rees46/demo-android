package rees46.demo_android.feature.productDetails.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rees46.demo_android.feature.productDetails.presentation.ProductAction
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.productDetails.domain.usecase.AddProductToCartUseCase
import rees46.demo_android.feature.productDetails.domain.usecase.GetCartProductUseCase
import rees46.demo_android.feature.productDetails.domain.usecase.GetRecommendationForProductUseCase
import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation

class ProductDetailsViewModel(
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getCartProductUseCase: GetCartProductUseCase,
    private val getRecommendationForProductUseCase: GetRecommendationForProductUseCase,
    product: Product
) : ViewModel() {

    private val _recommendationFlow: MutableStateFlow<Recommendation> = MutableStateFlow(Recommendation())
    val recommendationFlow: Flow<Recommendation> = _recommendationFlow

    private val _currentProductFlow: MutableStateFlow<Product> = MutableStateFlow(product)
    val currentProductFlow: Flow<Product> = _currentProductFlow

    private var _countCartProductFlow: MutableStateFlow<Int> =
        MutableStateFlow(getCartProductUseCase.execute(product.id)?.quantity ?: 1)
    var countCartProductFlow: Flow<Int> = _countCartProductFlow

    fun updateProduct(product: Product) {
        _countCartProductFlow.update { getCartProductUseCase.execute(product.id)?.quantity ?: 1 }
        _currentProductFlow.update { product }
    }

    fun updateRecommendationBlock(productId: String) {
        getRecommendationForProductUseCase.execute(
            recommenderCode = RECOMMENDER_CODE,
            productId = productId,
            onGetRecommendation = {
                viewModelScope.launch { _recommendationFlow.emit(it) }
            }
        )
    }

    fun proceedCartAction(action: ProductAction) {
        when (action) {
            ProductAction.ADD -> addToCart()
            ProductAction.INCREASE -> increaseCount()
            ProductAction.DECREASE -> decreaseCount()
        }
    }

    private fun addToCart() {
        addProductToCartUseCase.execute(
            product = _currentProductFlow.value,
            quantity = _countCartProductFlow.value
        )
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

package personaclick.demo_android.feature.productDetails.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import personaclick.demo_android.feature.productDetails.domain.models.Product
import personaclick.demo_android.feature.productDetails.domain.usecase.AddProductToCartUseCase
import personaclick.demo_android.feature.productDetails.domain.usecase.GetCartProductUseCase
import personaclick.demo_android.feature.productDetails.domain.usecase.GetRecommendationForProductUseCase
import personaclick.demo_android.feature.productDetails.presentation.ProductAction
import personaclick.demo_android.feature.recommendationBlock.domain.models.Recommendation

class ProductDetailsViewModel(
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val getCartProductUseCase: GetCartProductUseCase,
    private val getRecommendationForProductUseCase: GetRecommendationForProductUseCase,
    private val recommendedCode: String,
    product: Product?
) : ViewModel() {

    private val _recommendationFlow: MutableStateFlow<Recommendation> = MutableStateFlow(Recommendation("", emptyList()))
    val recommendationFlow: StateFlow<Recommendation> = _recommendationFlow.asStateFlow()

    private val _currentProductFlow: MutableStateFlow<Product?> = MutableStateFlow(product)
    val currentProductFlow: StateFlow<Product?> = _currentProductFlow.asStateFlow()

    private var _countCartProductFlow: MutableStateFlow<Int> =
        MutableStateFlow(if(product != null) getCartProductUseCase.invoke(product.id)?.quantity ?: 1 else 0)
    var countCartProductFlow: StateFlow<Int> = _countCartProductFlow.asStateFlow()

    fun updateProduct(product: Product?) {
        _countCartProductFlow.update { product?.let { getCartProductUseCase.invoke(product.id)?.quantity ?: 1 } ?:0 }
        _currentProductFlow.update { product }
    }

    fun updateRecommendationBlock(productId: String) {
        getRecommendationForProductUseCase.invoke(
            recommenderCode = recommendedCode,
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
        _currentProductFlow.value?.let { product ->
            addProductToCartUseCase.invoke(
                product = product,
                quantity = _countCartProductFlow.value
            )
        }
    }

    private fun increaseCount() = _countCartProductFlow.update { it.inc() }

    private fun decreaseCount() {
        if (_countCartProductFlow.value > 1) {
            _countCartProductFlow.update { it.dec() }
        }
    }
}

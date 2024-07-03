package rees46.demo_android.features.cardProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import rees46.demo_android.features.main.cart.Cart
import rees46.demo_android.entity.productsEntity.ProductEntity
import rees46.demo_android.features.recommendationBlock.RecommendationUtils

class CardProductViewModel(
    private val sdk: SDK,
    args: CardProductFragmentArgs
) : ViewModel() {
    private val _recommendedProductsFlow: MutableSharedFlow<ArrayList<ProductEntity>> = MutableSharedFlow()
    val recommendedProductsFlow: Flow<List<ProductEntity>> = _recommendedProductsFlow
    private val recommendedProducts = arrayListOf<ProductEntity>()

    private val _currentProductFlow: MutableStateFlow<ProductEntity> = MutableStateFlow(args.product)
    val currentProductFlow: Flow<ProductEntity> = _currentProductFlow

    private var _count: MutableSharedFlow<Int> = MutableSharedFlow()
    internal var count: Flow<Int> = _count
    private var countValue: Int = 1

    private var product: ProductEntity = args.product

    internal fun updateProduct(product: ProductEntity) {
        changeCount(1)
        this.product = product
        _currentProductFlow.update { this.product }
    }

    internal fun updateRecommendationBlock(productId: String) {
        recommendedProducts.clear()

        val params = Params().apply {
            put(Params.Parameter.ITEM, productId)
        }

        RecommendationUtils.updateExtendedRecommendation(
            sdk,
            RECOMMENDER_CODE,
            params,
            recommendedProducts,
            _recommendedProductsFlow,
            viewModelScope)
    }

    fun proceedCartAction(action: CardAction) {
        when(action) {
            CardAction.ADD -> addToCart()
            CardAction.INCREASE -> increaseCount()
            CardAction.DECREASE -> decreaseCount()
        }
    }

    private fun addToCart() {
        Cart.addProduct(product, countValue)
        sdk.trackEventManager.track(Params.TrackEvent.VIEW, product.id)
    }

    private fun increaseCount() {
        changeCount(countValue + 1)
    }

    private fun decreaseCount() {
        if(countValue > 1){
            changeCount(countValue - 1)
        }
    }

    private fun changeCount(value: Int) {
        countValue = value
        viewModelScope.launch {
            _count.emit(countValue)
        }
    }

    companion object {
        var RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

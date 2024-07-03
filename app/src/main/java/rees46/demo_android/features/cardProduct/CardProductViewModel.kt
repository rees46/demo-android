package rees46.demo_android.features.cardProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.main.cart.Cart
import rees46.demo_android.entity.productsEntity.ProductEntity
import rees46.demo_android.features.recommendationBlock.RecommendationUtils

class CardProductViewModel(
    private val sdk: SDK
) : ViewModel() {
    private val _recommendedProductsFlow: MutableSharedFlow<ArrayList<ProductEntity>> = MutableSharedFlow()
    val recommendedProductsFlow: Flow<List<ProductEntity>> = _recommendedProductsFlow
    private val recommendedProducts = arrayListOf<ProductEntity>()

    private var _count: MutableSharedFlow<Int> = MutableSharedFlow()
    internal var count: Flow<Int> = _count
    private var countValue: Int = 1

    private lateinit var product: ProductEntity

    internal fun updateProduct(product: ProductEntity) {
        changeCount(1)

        this.product = product
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

    internal fun addToCart() {
        Cart.addProduct(product, countValue)

        sdk.trackEventManager.track(Params.TrackEvent.VIEW, product.id)
    }

    internal fun increaseCount() {
        changeCount(countValue + 1)
    }

    internal fun decreaseCount() {
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

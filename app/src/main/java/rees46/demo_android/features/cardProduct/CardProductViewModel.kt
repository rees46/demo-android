package rees46.demo_android.features.cardProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.recommendationBlock.RecommendationUtils

class CardProductViewModel(
    private val sdk: SDK
) : ViewModel() {
    private val _recommendedProductsFlow: MutableSharedFlow<ArrayList<Product>> = MutableSharedFlow()
    val recommendedProductsFlow: Flow<List<Product>> = _recommendedProductsFlow
    private val recommendedProducts = arrayListOf<Product>()

    private var _count: MutableSharedFlow<Int> = MutableSharedFlow()
    internal var count: Flow<Int> = _count
    private var countValue: Int = 1

    private var productId: String = ""

    internal fun updateProduct(productId: String) {
        changeCount(1)

        this.productId = productId
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
        sdk.cartManager.addToCart(productId, countValue)
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

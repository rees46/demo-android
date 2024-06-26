package rees46.demo_android.features.cardProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.Params
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.recommendationBlock.RecommendationUtils

class CardProductViewModel(
    private val sdk: SDK
) : ViewModel() {
    private val _recommendedProductsFlow: MutableSharedFlow<ArrayList<Product>> = MutableSharedFlow()
    val recommendedProductsFlow: Flow<List<Product>> = _recommendedProductsFlow
    private val recommendedProducts = arrayListOf<Product>()

    internal fun updateRecommendationBlock(productId: String) {
        recommendedProducts.clear()

        val params = Params().apply {
            put(Params.Parameter.EXTENDED, true)
            put(Params.Parameter.ITEM, productId)
        }

        RecommendationUtils.updateRecommendation(
            sdk,
            RECOMMENDER_CODE,
            params,
            recommendedProducts,
            _recommendedProductsFlow,
            viewModelScope)
    }

    companion object {
        var RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

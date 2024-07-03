package rees46.demo_android.features.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import rees46.demo_android.entity.productsEntity.ProductEntity
import rees46.demo_android.features.recommendationBlock.RecommendationUtils

class HomeViewModel(
    sdk: SDK
) : ViewModel() {

    private val _recommendationProductsFlow: MutableSharedFlow<ArrayList<ProductEntity>> = MutableSharedFlow()
    val recommendationProductsFlow: Flow<List<ProductEntity>> = _recommendationProductsFlow
    private val recommendationProducts = arrayListOf<ProductEntity>()

    init {
        RecommendationUtils.updateExtendedRecommendation(
            sdk,
            RECOMMENDER_CODE,
            recommendationProducts,
            _recommendationProductsFlow,
            viewModelScope)
    }

    companion object {
        var RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

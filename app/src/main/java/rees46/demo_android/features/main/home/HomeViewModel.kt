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

    private val _topTrendsProductsFlow: MutableSharedFlow<ArrayList<ProductEntity>> = MutableSharedFlow()
    val topTrendsProductsFlow: Flow<List<ProductEntity>> = _topTrendsProductsFlow
    private val topTrendsProducts = arrayListOf<ProductEntity>()

    init {
        RecommendationUtils.updateExtendedRecommendation(
            sdk,
            TOP_TRENDS_RECOMMENDER_CODE,
            topTrendsProducts,
            _topTrendsProductsFlow,
            viewModelScope)
    }

    companion object {
        var TOP_TRENDS_RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

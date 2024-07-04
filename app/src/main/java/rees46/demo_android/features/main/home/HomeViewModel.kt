package rees46.demo_android.features.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.entity.productsEntity.ProductEntity
import rees46.demo_android.features.recommendationBlock.RecommendationUtils

class HomeViewModel(
    sdk: SDK
) : ViewModel() {

    private val _recommendationProductsFlow: MutableSharedFlow<MutableList<ProductEntity>> = MutableSharedFlow()
    val recommendationProductsFlow: Flow<List<ProductEntity>> = _recommendationProductsFlow

    init {
        RecommendationUtils.updateExtendedRecommendation(sdk, RECOMMENDER_CODE) {
            viewModelScope.launch { _recommendationProductsFlow.emit(it) }
        }
    }

    companion object {
        var RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

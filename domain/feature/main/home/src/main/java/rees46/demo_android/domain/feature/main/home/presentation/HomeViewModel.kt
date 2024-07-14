package rees46.demo_android.domain.feature.main.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.domain.entities.ProductDto

class HomeViewModel(
    sdk: SDK
) : ViewModel() {

    private val _recommendationProductsFlow: MutableSharedFlow<MutableList<ProductDto>> = MutableSharedFlow()
    val recommendationProductsFlow: Flow<List<ProductDto>> = _recommendationProductsFlow

    init {
        rees46.demo_android.domain.feature.recommendation_block.utils.RecommendationUtils.updateExtendedRecommendation(sdk, RECOMMENDER_CODE) {
            viewModelScope.launch { _recommendationProductsFlow.emit(it) }
        }
    }

    companion object {
        var RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

package rees46.demo_android.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.usecase.recommendation.GetRecommendationUseCase

class HomeViewModel(
    getRecommendationUseCase: GetRecommendationUseCase
) : ViewModel() {

    private val _recommendationProductsFlow: MutableSharedFlow<MutableList<ProductEntity>> = MutableSharedFlow()
    val recommendationProductsFlow: Flow<List<ProductEntity>> = _recommendationProductsFlow

    init {
        getRecommendationUseCase(RECOMMENDER_CODE) {
            viewModelScope.launch { _recommendationProductsFlow.emit(it) }
        }
    }

    companion object {
        var RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

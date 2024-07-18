package rees46.demo_android.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation
import rees46.demo_android.feature.recommendationBlock.domain.usecase.GetRecommendationUseCase

class HomeViewModel(
    getRecommendationUseCase: GetRecommendationUseCase
) : ViewModel() {

    private val _recommendationFlow: MutableSharedFlow<Recommendation> = MutableSharedFlow()
    val recommendationFlow: Flow<Recommendation> = _recommendationFlow

    init {
        getRecommendationUseCase.execute(
            recommenderCode = RECOMMENDER_CODE,
            onGetRecommendation = {
                viewModelScope.launch { _recommendationFlow.emit(it) }
            }
        )
    }

    companion object {
        var RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

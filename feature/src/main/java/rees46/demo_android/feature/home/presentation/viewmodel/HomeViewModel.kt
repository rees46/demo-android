package rees46.demo_android.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation
import rees46.demo_android.feature.recommendationBlock.domain.usecase.GetRecommendationUseCase

class HomeViewModel(
    getRecommendationUseCase: GetRecommendationUseCase,
    recommenderCode: String
) : ViewModel() {

    private val _recommendationFlow: MutableStateFlow<Recommendation> = MutableStateFlow(Recommendation("", emptyList()))
    val recommendationFlow: Flow<Recommendation> = _recommendationFlow

    init {
        getRecommendationUseCase.execute(
            recommenderCode = recommenderCode,
            onGetRecommendation = {
                viewModelScope.launch { _recommendationFlow.emit(it) }
            }
        )
    }
}

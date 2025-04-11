package personaclick.demo_android.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import personaclick.demo_android.feature.recommendationBlock.domain.models.Recommendation
import personaclick.demo_android.feature.recommendationBlock.domain.usecase.GetRecommendationUseCase

class HomeViewModel(
    getRecommendationUseCase: GetRecommendationUseCase,
    recommenderCode: String
) : ViewModel() {

    private val _recommendationFlow: MutableStateFlow<Recommendation> = MutableStateFlow(Recommendation("", emptyList()))
    val recommendationFlow: StateFlow<Recommendation> = _recommendationFlow.asStateFlow()

    init {
        getRecommendationUseCase.invoke(
            recommenderCode = recommenderCode,
            onGetRecommendation = {
                viewModelScope.launch { _recommendationFlow.emit(it) }
            }
        )
    }
}

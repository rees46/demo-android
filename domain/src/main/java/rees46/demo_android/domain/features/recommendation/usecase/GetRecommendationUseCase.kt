package rees46.demo_android.domain.features.recommendation.usecase

import rees46.demo_android.domain.models.RecommendationDto
import rees46.demo_android.domain.repository.RecommendationRepository

class GetRecommendationUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    operator fun invoke(
        recommenderCode: String,
        onGetRecommendation: (RecommendationDto) -> Unit
    ) {
        recommendationRepository.getRecommendation(recommenderCode, onGetRecommendation)
    }
}

package rees46.demo_android.domain.features.recommendation.usecase

import rees46.demo_android.domain.entities.RecommendationEntity
import rees46.demo_android.domain.repository.RecommendationRepository

class GetRecommendationUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    operator fun invoke(
        recommenderCode: String,
        onGetRecommendation: (RecommendationEntity) -> Unit
    ) {
        recommendationRepository.getRecommendation(recommenderCode, onGetRecommendation)
    }
}

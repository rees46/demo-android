package rees46.demo_android.feature.recommendationBlock.domain.usecase

import rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto
import rees46.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository

class GetRecommendationUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    operator fun invoke(
        recommenderCode: String,
        onGetRecommendation: (RecommendationDto) -> Unit
    ) {
        recommendationRepository.getRecommendation(
            recommenderCode = recommenderCode,
            onGetRecommendation = onGetRecommendation
        )
    }
}

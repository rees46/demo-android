package personaclick.demo_android.feature.recommendationBlock.domain.usecase

import personaclick.demo_android.feature.recommendationBlock.domain.models.Recommendation
import personaclick.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository

class GetRecommendationUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    fun invoke(
        recommenderCode: String,
        onGetRecommendation: (Recommendation) -> Unit
    ) {
        recommendationRepository.getRecommendation(
            recommenderCode = recommenderCode,
            onGetRecommendation = onGetRecommendation
        )
    }
}

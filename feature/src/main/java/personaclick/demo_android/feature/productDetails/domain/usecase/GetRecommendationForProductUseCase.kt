package personaclick.demo_android.feature.productDetails.domain.usecase

import personaclick.demo_android.feature.recommendationBlock.domain.models.Recommendation
import personaclick.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository

class GetRecommendationForProductUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    fun invoke(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (Recommendation) -> Unit
    ) {
        recommendationRepository.getRecommendationForProduct(
            recommenderCode = recommenderCode,
            productId = productId,
            onGetRecommendation = onGetRecommendation
        )
    }
}

package rees46.demo_android.feature.productDetails.domain.usecase

import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation
import rees46.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository

class GetRecommendationForProductUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    fun execute(
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

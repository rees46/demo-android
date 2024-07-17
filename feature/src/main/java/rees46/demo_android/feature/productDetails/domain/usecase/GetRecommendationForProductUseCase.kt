package rees46.demo_android.feature.productDetails.domain.usecase

import rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto
import rees46.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository

class GetRecommendationForProductUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    operator fun invoke(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (RecommendationDto) -> Unit
    ) {
        recommendationRepository.getRecommendationForProduct(recommenderCode, productId, onGetRecommendation)
    }
}

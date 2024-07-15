package rees46.demo_android.domain.features.cardProduct.usecase

import rees46.demo_android.domain.models.RecommendationDto
import rees46.demo_android.domain.repository.RecommendationRepository

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

package rees46.demo_android.domain.usecase.recommendation

import rees46.demo_android.domain.entities.ProductDto
import rees46.demo_android.domain.repository.RecommendationRepository

class GetRecommendationForProductUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    operator fun invoke(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (MutableList<ProductDto>) -> Unit
    ) {
        recommendationRepository.getRecommendationForProduct(recommenderCode, productId, onGetRecommendation)
    }
}

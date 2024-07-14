package rees46.demo_android.domain.usecase.recommendation

import rees46.demo_android.domain.entities.ProductDto
import rees46.demo_android.domain.repository.RecommendationRepository

class GetRecommendationUseCase (
    private val recommendationRepository: RecommendationRepository
) {

    operator fun invoke(
        recommenderCode: String,
        onGetRecommendation: (MutableList<ProductDto>) -> Unit
    ) {
        recommendationRepository.getRecommendation(recommenderCode, onGetRecommendation)
    }
}

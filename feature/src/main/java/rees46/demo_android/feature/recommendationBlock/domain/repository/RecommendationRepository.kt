package rees46.demo_android.feature.recommendationBlock.domain.repository

import rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto

interface RecommendationRepository {

    fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (RecommendationDto) -> Unit
    )

    fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (RecommendationDto) -> Unit
    )
}

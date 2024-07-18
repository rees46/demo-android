package rees46.demo_android.feature.recommendationBlock.domain.repository

import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation

interface RecommendationRepository {

    fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (Recommendation) -> Unit
    )

    fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (Recommendation) -> Unit
    )
}

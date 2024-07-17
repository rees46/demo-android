package rees46.demo_android.feature.recommendationBlock.domain.repository

interface RecommendationRepository {

    fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto) -> Unit
    )

    fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto) -> Unit
    )
}

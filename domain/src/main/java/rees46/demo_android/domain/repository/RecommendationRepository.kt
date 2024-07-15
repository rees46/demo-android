package rees46.demo_android.domain.repository

import rees46.demo_android.domain.entities.RecommendationEntity

interface RecommendationRepository {

    fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (RecommendationEntity) -> Unit
    )

    fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (RecommendationEntity) -> Unit
    )
}

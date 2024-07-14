package rees46.demo_android.domain.repository

import rees46.demo_android.domain.entities.ProductDto

interface RecommendationRepository {

    fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (MutableList<ProductDto>) -> Unit
    )

    fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (MutableList<ProductDto>) -> Unit
    )
}

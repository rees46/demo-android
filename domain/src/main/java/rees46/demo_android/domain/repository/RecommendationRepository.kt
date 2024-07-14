package rees46.demo_android.domain.repository

import rees46.demo_android.domain.entities.ProductEntity

interface RecommendationRepository {

    fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (MutableList<ProductEntity>) -> Unit
    )

    fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (MutableList<ProductEntity>) -> Unit
    )
}

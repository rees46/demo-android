package rees46.demo_android.data.repository.recommendation

import com.personalizatio.SDK
import com.personalizatio.Params
import rees46.demo_android.domain.entities.ProductDto
import rees46.demo_android.domain.feature.utils.createProductList
import rees46.demo_android.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl (
    private val sdk: SDK
) : RecommendationRepository {

    override fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (MutableList<ProductDto>) -> Unit
    ) {
        getRecommendation(recommenderCode, Params(), onGetRecommendation)
    }

    override fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (MutableList<ProductDto>) -> Unit
    ) {
        getRecommendation(recommenderCode, createRecommendationParams(productId), onGetRecommendation)
    }

    private fun getRecommendation(
        recommenderCode: String,
        params: Params,
        onGetRecommendation: (MutableList<ProductDto>) -> Unit
    ) {
        sdk.recommendationManager.getExtendedRecommendation(
            recommenderCode = recommenderCode,
            params = params,
            onGetExtendedRecommendation = {
                onGetRecommendation.invoke(it.products.createProductList())
            }
        )
    }

    private fun createRecommendationParams(productId: String, parameter: Params.Parameter = Params.Parameter.ITEM): Params {
        return Params().apply { put(parameter, productId) }
    }
}

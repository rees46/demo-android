package rees46.demo_android.feature.recommendationBlock.data.repository

import com.personalizatio.SDK
import com.personalizatio.Params
import com.personalizatio.api.responses.recommendation.GetExtendedRecommendationResponse
import rees46.demo_android.feature.product.data.utils.ProductUtils.toProducts
import rees46.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl (
    private val sdk: SDK
) : RecommendationRepository {

    override fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto) -> Unit
    ) {
        getRecommendation(recommenderCode, Params(), onGetRecommendation)
    }

    override fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto) -> Unit
    ) {
        getRecommendation(recommenderCode, createRecommendationParams(productId), onGetRecommendation)
    }

    private fun getRecommendation(
        recommenderCode: String,
        params: Params,
        onGetRecommendation: (rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto) -> Unit
    ) {
        sdk.recommendationManager.getExtendedRecommendation(
            recommenderCode = recommenderCode,
            params = params,
            onGetExtendedRecommendation = {
                onGetRecommendation.invoke(it.toRecommendation())
            }
        )
    }

    private fun createRecommendationParams(productId: String, parameter: Params.Parameter = Params.Parameter.ITEM): Params {
        return Params().apply { put(parameter, productId) }
    }

    private fun GetExtendedRecommendationResponse.toRecommendation() =
        rees46.demo_android.feature.recommendationBlock.domain.models.RecommendationDto(
            title,
            products.toProducts()
        )
}

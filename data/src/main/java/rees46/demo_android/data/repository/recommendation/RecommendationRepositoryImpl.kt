package rees46.demo_android.data.repository.recommendation

import com.personalizatio.SDK
import com.personalizatio.Params
import com.personalizatio.api.responses.recommendation.GetExtendedRecommendationResponse
import rees46.demo_android.data.utils.ProductUtils.toProducts
import rees46.demo_android.domain.entities.RecommendationEntity
import rees46.demo_android.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl (
    private val sdk: SDK
) : RecommendationRepository {

    override fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (RecommendationEntity) -> Unit
    ) {
        getRecommendation(recommenderCode, Params(), onGetRecommendation)
    }

    override fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (RecommendationEntity) -> Unit
    ) {
        getRecommendation(recommenderCode, createRecommendationParams(productId), onGetRecommendation)
    }

    private fun getRecommendation(
        recommenderCode: String,
        params: Params,
        onGetRecommendation: (RecommendationEntity) -> Unit
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
        RecommendationEntity(title, products.toProducts())
}

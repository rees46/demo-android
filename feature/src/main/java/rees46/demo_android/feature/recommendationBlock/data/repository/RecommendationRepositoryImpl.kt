package rees46.demo_android.feature.recommendationBlock.data.repository

import com.personalizatio.Params
import rees46.demo_android.feature.search.data.repository.SearchRepositoryImpl.Companion.toProducts
import rees46.demo_android.feature.recommendationBlock.data.api.RecommendationApi
import rees46.demo_android.feature.recommendationBlock.data.models.RecommendationDto
import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation
import rees46.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl (
    private val recommendationApi: RecommendationApi
) : RecommendationRepository {

    override fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (Recommendation) -> Unit
    ) {
        getRecommendation(
            recommenderCode = recommenderCode,
            params = Params(),
            onGetRecommendation = onGetRecommendation
        )
    }

    override fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (Recommendation) -> Unit
    ) {
        getRecommendation(
            recommenderCode = recommenderCode,
            params = createRecommendationParams(productId),
            onGetRecommendation = onGetRecommendation
        )
    }

    private fun getRecommendation(
        recommenderCode: String,
        params: Params,
        onGetRecommendation: (Recommendation) -> Unit
    ) {
        recommendationApi.getRecommendation(
            recommenderCode = recommenderCode,
            params = params,
            onGetRecommendation = {
                onGetRecommendation.invoke(it.toRecommendation())
            }
        )
    }

    private fun createRecommendationParams(
        productId: String,
        parameter: Params.Parameter = Params.Parameter.ITEM
    ) = Params().put(
            param = parameter,
            value = productId
        )

    private fun RecommendationDto.toRecommendation() : Recommendation =
        Recommendation(
            title = title,
            products = products.toProducts()
        )
}

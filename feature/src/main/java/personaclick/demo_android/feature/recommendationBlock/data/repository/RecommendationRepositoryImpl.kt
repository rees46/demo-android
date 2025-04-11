package personaclick.demo_android.feature.recommendationBlock.data.repository

import com.personalization.Params
import personaclick.demo_android.feature.recommendationBlock.data.api.RecommendationApi
import personaclick.demo_android.feature.recommendationBlock.data.mappers.RecommendationMapper
import personaclick.demo_android.feature.recommendationBlock.domain.models.Recommendation
import personaclick.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl (
    private val recommendationApi: RecommendationApi,
    private val recommendationMapper: RecommendationMapper
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
                onGetRecommendation.invoke(recommendationMapper.toRecommendation(it))
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
}

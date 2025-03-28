package personaclick.demo_android.feature.recommendationBlock.data.api

import com.personalization.Params
import com.personalization.SDK
import com.personalization.api.responses.recommendation.GetExtendedRecommendationResponse
import personaclick.demo_android.feature.recommendationBlock.data.models.RecommendationDto
import personaclick.demo_android.feature.search.data.api.toProducts

class RecommendationApi(
    private val sdk: SDK
) {

    fun getRecommendation(
        recommenderCode: String,
        params: Params,
        onGetRecommendation: (RecommendationDto) -> Unit
    ) {
        sdk.recommendationManager.getExtendedRecommendation(
            recommenderCode = recommenderCode,
            params = params,
            onGetExtendedRecommendation = {
                onGetRecommendation.invoke(it.toRecommendation())
            }
        )
    }

    private fun GetExtendedRecommendationResponse.toRecommendation(): RecommendationDto {
        return RecommendationDto(
            title = title,
            products = products.toProducts()
        )
    }
}

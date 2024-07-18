package rees46.demo_android.feature.recommendationBlock.data.api

import com.personalizatio.Params
import com.personalizatio.SDK
import com.personalizatio.api.responses.recommendation.GetExtendedRecommendationResponse
import rees46.demo_android.feature.recommendationBlock.data.models.RecommendationDto
import rees46.demo_android.feature.search.data.api.toProducts

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

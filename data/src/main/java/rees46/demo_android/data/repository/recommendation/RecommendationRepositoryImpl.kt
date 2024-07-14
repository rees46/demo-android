package rees46.demo_android.data.repository.recommendation

import com.personalizatio.SDK
import com.personalizatio.Params
import rees46.demo_android.data.utils.ProductUtils.toProducts
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.repository.RecommendationRepository

class RecommendationRepositoryImpl (
    private val sdk: SDK
) : RecommendationRepository {

    override fun getRecommendation(
        recommenderCode: String,
        onGetRecommendation: (MutableList<ProductEntity>) -> Unit
    ) {
        getRecommendation(recommenderCode, Params(), onGetRecommendation)
    }

    override fun getRecommendationForProduct(
        recommenderCode: String,
        productId: String,
        onGetRecommendation: (MutableList<ProductEntity>) -> Unit
    ) {
        getRecommendation(recommenderCode, createRecommendationParams(productId), onGetRecommendation)
    }

    private fun getRecommendation(
        recommenderCode: String,
        params: Params,
        onGetRecommendation: (MutableList<ProductEntity>) -> Unit
    ) {
        sdk.recommendationManager.getExtendedRecommendation(
            recommenderCode = recommenderCode,
            params = params,
            onGetExtendedRecommendation = {
                onGetRecommendation.invoke(it.products.toProducts())
            }
        )
    }

    private fun createRecommendationParams(productId: String, parameter: Params.Parameter = Params.Parameter.ITEM): Params {
        return Params().apply { put(parameter, productId) }
    }
}

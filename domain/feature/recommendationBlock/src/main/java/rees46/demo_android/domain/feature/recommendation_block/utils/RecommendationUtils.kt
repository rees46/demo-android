package rees46.demo_android.domain.feature.recommendation_block.utils

import com.personalizatio.Params
import com.personalizatio.SDK
import rees46.demo_android.domain.entities.ProductDto
import rees46.demo_android.domain.feature.utils.createProductList

object RecommendationUtils {

    private fun createRecommendationParams(productId: String, parameter: Params.Parameter = Params.Parameter.ITEM): Params {
        return Params().apply { put(parameter, productId) }
    }

    fun updateExtendedRecommendation(
        sdk: SDK,
        recommenderCode: String,
        onGetExtendedRecommendation: (MutableList<ProductDto>) -> Unit
    ) {
        updateExtendedRecommendation(
            sdk = sdk,
            recommenderCode = recommenderCode,
            params = Params(),
            onGetExtendedRecommendation = onGetExtendedRecommendation
        )
    }

    fun updateExtendedRecommendationForProduct(
        sdk: SDK,
        recommenderCode: String,
        productId: String,
        onGetExtendedRecommendation: (MutableList<ProductDto>) -> Unit
    ) {
        updateExtendedRecommendation(
            sdk = sdk,
            recommenderCode = recommenderCode,
            params = createRecommendationParams(productId),
            onGetExtendedRecommendation = onGetExtendedRecommendation
        )
    }

    internal fun updateExtendedRecommendation(
        sdk: SDK,
        recommenderCode: String,
        params: Params,
        onGetExtendedRecommendation: (MutableList<ProductDto>) -> Unit
    ) {
        sdk.recommendationManager.getExtendedRecommendation(
            recommenderCode = recommenderCode,
            params = params,
            onGetExtendedRecommendation = {
                onGetExtendedRecommendation.invoke(it.products.createProductList())
            }
        )
    }
}

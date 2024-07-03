package rees46.demo_android.features.recommendationBlock

import com.personalizatio.Params
import com.personalizatio.SDK
import com.personalizatio.api.entities.recommendation.ExtendedRecommendationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.product.ProductUtils

object RecommendationUtils {

    internal fun updateExtendedRecommendation(
        sdk: SDK,
        recommenderCode: String,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) {
        updateExtendedRecommendation(sdk, recommenderCode, Params(), products, productsFlow, scope)
    }

    internal fun updateExtendedRecommendation(
        sdk: SDK,
        recommenderCode: String,
        params: Params,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) {
        sdk.recommendationManager.getExtendedRecommendation(
            recommenderCode = recommenderCode,
            params = params,
            onGetExtendedRecommendation = { extendedRecommendationEntity: ExtendedRecommendationEntity ->
                for (productEntity in extendedRecommendationEntity.products) {
                    val product = ProductUtils.createProduct(productEntity)

                    products.add(product)
                    scope.launch {
                        productsFlow.emit(products)
                    }
                }
            }
        )
    }
}

package rees46.demo_android.features.recommendationBlock

import com.personalizatio.Params
import com.personalizatio.SDK
import com.personalizatio.api.listeners.OnProductsListener
import com.personalizatio.api.listeners.OnRecommendationListener
import com.personalizatio.entities.products.productInfo.ProductInfoEntity
import com.personalizatio.entities.recommendation.ExtendedRecommendationEntity
import com.personalizatio.entities.recommendation.RecommendationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.product.ProductUtils

object RecommendationUtils {

    internal fun updateExtendedRecommendation(
        sdk: SDK,
        recommenderCode: String,
        params: Params,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) {
        val listener = createOnRecommendationListener(sdk, products, productsFlow, scope)
        sdk.recommendationManager.getExtendedRecommendation(recommenderCode, params, listener)
    }

    internal fun updateExtendedRecommendation(
        sdk: SDK,
        recommenderCode: String,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) {
        val listener = createOnRecommendationListener(sdk, products, productsFlow, scope)
        sdk.recommendationManager.getExtendedRecommendation(recommenderCode, listener)
    }

    private fun createOnRecommendationListener(
        sdk: SDK,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) : OnRecommendationListener {
        fun addProduct(product: Product) {
            products.add(product)
            scope.launch {
                productsFlow.emit(products)
            }
        }

        return object: OnRecommendationListener {
            override fun onGetRecommendation(recommendationEntity: RecommendationEntity) {
                for (productId in recommendationEntity.productIds) {
                    sdk.productsManager.getProductInfo(productId.toString(), object: OnProductsListener {
                        override fun onGetProductInfo(productInfoEntity: ProductInfoEntity) {
                            val product = ProductUtils.createProduct(productInfoEntity)
                            addProduct(product)
                        }
                    })
                }
            }

            override fun onGetExtendedRecommendation(extendedRecommendationEntity: ExtendedRecommendationEntity) {
                for (productEntity in extendedRecommendationEntity.products) {
                    val product = ProductUtils.createProduct(productEntity)
                    addProduct(product)
                }
            }
        }
    }
}

package rees46.demo_android.features.recommendationBlock

import com.personalizatio.Params
import com.personalizatio.SDK
import com.personalizatio.api.listeners.OnProductsListener
import com.personalizatio.api.listeners.OnRecommendationListener
import com.personalizatio.entities.products.productInfo.ProductInfoEntity
import com.personalizatio.entities.recommended.RecommendedEntity
import com.personalizatio.entities.recommended.RecommendedFullEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.product.ProductUtils

object RecommendationUtils {

    internal fun updateRecommendation(
        sdk: SDK,
        recommenderCode: String,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) {
        val listener = createOnRecommendationListener(sdk, products, productsFlow, scope)
        sdk.recommendationManager.recommend(recommenderCode, listener)
    }

    internal fun updateRecommendation(
        sdk: SDK,
        recommenderCode: String,
        params: Params,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) {
        val listener = createOnRecommendationListener(sdk, products, productsFlow, scope)
        sdk.recommendationManager.recommend(recommenderCode, params, listener)
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
            override fun onGetRecommended(recommendedEntity: RecommendedEntity) {
                for (productId in recommendedEntity.productIds) {
                    sdk.productsManager.getProductInfo(productId.toString(), object: OnProductsListener {
                        override fun onGetProductInfo(productInfoEntity: ProductInfoEntity) {
                            val product = ProductUtils.createProduct(productInfoEntity)
                            addProduct(product)
                        }
                    })
                }
            }

            override fun onGetRecommended(recommendedFullEntity: RecommendedFullEntity) {
                for (recommend in recommendedFullEntity.recommends) {
                    val product = ProductUtils.createProduct(recommend)
                    addProduct(product)
                }
            }
        }
    }
}

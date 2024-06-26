package rees46.demo_android.features.recommendationBlock

import com.personalizatio.Params
import com.personalizatio.SDK
import com.personalizatio.entities.products.productInfo.ProductInfoEntity
import com.personalizatio.entities.recommended.Recommend
import com.personalizatio.entities.recommended.RecommendedEntity
import com.personalizatio.entities.recommended.RecommendedFullEntity
import com.personalizatio.products.OnProductsListener
import com.personalizatio.recommended.OnRecommendedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product

object RecommendationUtils {

    internal fun updateRecommendation(
        sdk: SDK,
        recommenderCode: String,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) {
        val listener = createOnRecommendedListener(sdk, products, productsFlow, scope)
        sdk.recommend(recommenderCode, listener)
    }

    internal fun updateRecommendation(
        sdk: SDK,
        recommenderCode: String,
        params: Params,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) {
        val listener = createOnRecommendedListener(sdk, products, productsFlow, scope)
        sdk.recommend(recommenderCode, params, listener)
    }

    private fun createOnRecommendedListener(
        sdk: SDK,
        products: ArrayList<Product>,
        productsFlow: MutableSharedFlow<ArrayList<Product>>,
        scope: CoroutineScope
    ) : OnRecommendedListener {
        fun addProduct(product: Product) {
            products.add(product)
            scope.launch {
                productsFlow.emit(products)
            }
        }

        return object: OnRecommendedListener {
            override fun onGetRecommended(recommendedEntity: RecommendedEntity) {
                for (productId in recommendedEntity.productIds) {
                    sdk.getProductInfo(productId.toString(), object: OnProductsListener {
                        override fun onGetProductInfo(productInfoEntity: ProductInfoEntity) {
                            val product = createProduct(productInfoEntity)
                            addProduct(product)
                        }
                    })
                }
            }

            override fun onGetRecommended(recommendedFullEntity: RecommendedFullEntity) {
                for (recommend in recommendedFullEntity.recommends) {
                    val product = createProduct(recommend)
                    addProduct(product)
                }
            }
        }
    }

    private fun createProduct(productInfoEntity: ProductInfoEntity) : Product {
        return Product(
            id = productInfoEntity.uniqid,
            name = productInfoEntity.name,
            producerName = productInfoEntity.brand,
            price = Integer.getInteger(productInfoEntity.price),
            priceFormatted = productInfoEntity.price,
            priceFull = null,
            priceFullFormatted = null,
            pictureUrl = productInfoEntity.picture,
            description = productInfoEntity.description,
        )
    }

    private fun createProduct(recommend: Recommend) : Product {
        return Product(
            id = recommend.id,
            name = recommend.name,
            producerName = recommend.brand,
            price = recommend.price,
            priceFormatted = recommend.priceFormatted,
            priceFull = recommend.priceFull,
            priceFullFormatted = recommend.priceFullFormatted,
            pictureUrl = recommend.picture,
            description = recommend.description,
        )
    }
}

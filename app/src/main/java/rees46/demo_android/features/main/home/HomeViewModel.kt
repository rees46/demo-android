package rees46.demo_android.features.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import com.personalizatio.entities.products.productInfo.ProductInfoEntity
import com.personalizatio.entities.recommended.RecommendedEntity
import com.personalizatio.products.OnProductsListener
import com.personalizatio.recommended.OnRecommendedListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product

class HomeViewModel(
    private val sdk: SDK
) : ViewModel() {

    private val _topTrendsProductsFlow: MutableSharedFlow<ArrayList<Product>> = MutableSharedFlow()
    val topTrendsProductsFlow: Flow<List<Product>> = _topTrendsProductsFlow
    private val topTrendsProducts = arrayListOf<Product>()

    init {
        sdk.recommend(TOP_TRENDS_RECOMMENDER_CODE, object: OnRecommendedListener {
            override fun onGetRecommended(recommendedEntity: RecommendedEntity) {
                for (productId in recommendedEntity.productIds) {

                    sdk.getProductInfo(productId.toString(), object: OnProductsListener {
                        override fun onGetProductInfo(productInfoEntity: ProductInfoEntity) {
                            val product = createProduct(productInfoEntity)
                            topTrendsProducts.add(product)
                            viewModelScope.launch {
                                _topTrendsProductsFlow.emit(topTrendsProducts)
                            }
                        }
                    })
                }
            }
        })
    }

    internal fun getTopTrendsProduct(productId: String) : Product? {
        return topTrendsProducts.find { product -> product.id == productId }
    }

    private fun createProduct(productInfoEntity: ProductInfoEntity) : Product {
        return Product(
            productInfoEntity.uniqid,
            productInfoEntity.name,
            productInfoEntity.brand,
            productInfoEntity.price,
            productInfoEntity.salesRate,
            productInfoEntity.picture
        )
    }

    companion object {
        var TOP_TRENDS_RECOMMENDER_CODE = "a043dbc2f852ffe18861a2cdfc364ef2"
    }
}

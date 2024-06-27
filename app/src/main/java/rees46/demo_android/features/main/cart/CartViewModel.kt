package rees46.demo_android.features.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import com.personalizatio.entities.products.cart.CartEntity
import com.personalizatio.entities.products.productInfo.ProductInfoEntity
import com.personalizatio.products.OnProductsListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.product.ProductUtils

class CartViewModel(
    private val sdk: SDK
) : ViewModel() {

    private val _productsFlow: MutableSharedFlow<ArrayList<Product>> = MutableSharedFlow()
    val productsFlow: Flow<List<Product>> = _productsFlow
    private val products = arrayListOf<Product>()

    internal fun updateCart() {
        sdk.getCart(object : OnProductsListener() {
            override fun onGetCart(cartEntity: CartEntity) {
                for (item in cartEntity.data.items) {
                    sdk.getProductInfo(item.uniqid, object : OnProductsListener() {
                        override fun onGetProductInfo(productInfoEntity: ProductInfoEntity) {
                            val product = ProductUtils.createProduct(productInfoEntity)
                            products.add(product)
                            viewModelScope.launch {
                                _productsFlow.emit(products)
                            }
                        }
                    })
                }
            }
        })
    }
}

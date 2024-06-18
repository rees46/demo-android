package rees46.demo_android.features.main.home

import androidx.lifecycle.ViewModel
import rees46.demo_android.features.product.ModelBuilder
import rees46.demo_android.features.product.Product

class HomeViewModel : ViewModel() {

    internal val products: List<Product> = ModelBuilder.getProducts()

    internal fun getProduct(productId: Int) : Product? {
         return products.find { product -> product.productId == productId }
    }
}

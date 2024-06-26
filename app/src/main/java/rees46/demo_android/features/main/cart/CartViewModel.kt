package rees46.demo_android.features.main.cart

import androidx.lifecycle.ViewModel
import rees46.demo_android.features.product.Product

class CartViewModel : ViewModel() {

    internal fun getProduct(productId: String) : Product? {
        return null
        //return products.find { product -> product.id == productId }
    }
}

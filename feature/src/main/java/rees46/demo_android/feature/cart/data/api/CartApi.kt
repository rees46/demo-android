package rees46.demo_android.feature.cart.data.api

import com.personalizatio.Params
import com.personalizatio.SDK
import rees46.demo_android.feature.productDetails.domain.models.Product

class CartApi(
    private val sdk: SDK
) {

    fun addProduct(product: Product, quantity: Int) {
        sdk.trackEventManager.track(
            event = Params.TrackEvent.VIEW,
            productId = product.id
        )
    }

    fun removeProduct(productId: String) {
        sdk.trackEventManager.track(
            event = Params.TrackEvent.REMOVE_FROM_CART,
            params = Params(),
            listener = null
        )
    }
}

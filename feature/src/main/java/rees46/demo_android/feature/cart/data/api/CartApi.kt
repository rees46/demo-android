package rees46.demo_android.feature.cart.data.api

import com.personalizatio.Params
import com.personalizatio.SDK
import com.personalizatio.api.OnApiCallbackListener
import com.personalizatio.api.params.ProductItemParams
import rees46.demo_android.feature.productDetails.domain.models.Product

class CartApi(
    private val sdk: SDK
) {

    fun addProduct(
        product: Product,
        quantity: Int,
        listener: OnApiCallbackListener
    ) {
        val productItemParams = ProductItemParams(product.id)
            .set(ProductItemParams.PARAMETER.AMOUNT, quantity)

        sdk.trackEventManager.track(
            event = Params.TrackEvent.VIEW,
            params = Params().put(productItemParams),
            listener = listener
        )
    }

    fun removeProduct(
        productId: String,
        listener: OnApiCallbackListener
    ) {
        sdk.trackEventManager.track(
            event = Params.TrackEvent.REMOVE_FROM_CART,
            params = Params().put(ProductItemParams(productId)),
            listener = listener
        )
    }
}

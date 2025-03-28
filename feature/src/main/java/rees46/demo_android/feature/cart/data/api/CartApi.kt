package personaclick.demo_android.feature.cart.data.api

import com.personalization.Params
import com.personalization.SDK
import com.personalization.api.OnApiCallbackListener
import com.personalization.api.params.ProductItemParams
import personaclick.demo_android.feature.productDetails.domain.models.Product

class CartApi(
    private val sdk: SDK
) {

    fun addProduct(
        product: Product,
        quantity: Int,
        listener: OnApiCallbackListener
    ) {
        val productItemParams = ProductItemParams(product.id)
            .set(
                column = ProductItemParams.PARAMETER.AMOUNT,
                value = quantity
            )

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

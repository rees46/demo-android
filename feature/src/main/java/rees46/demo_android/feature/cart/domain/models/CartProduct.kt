package personaclick.demo_android.feature.cart.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import personaclick.demo_android.feature.productDetails.domain.models.Product

@Parcelize
data class CartProduct(
    val product: Product,
    var quantity: Int
) : Parcelable

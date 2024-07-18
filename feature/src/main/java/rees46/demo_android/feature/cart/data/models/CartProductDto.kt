package rees46.demo_android.feature.cart.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import rees46.demo_android.feature.productDetails.data.models.ProductDto

@Parcelize
data class CartProductDto(
    val product: ProductDto,
    var quantity: Int
) : Parcelable
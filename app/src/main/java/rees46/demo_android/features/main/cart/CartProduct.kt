package rees46.demo_android.features.main.cart

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import rees46.demo_android.features.product.Product

@Parcelize
data class CartProduct(
    val product: Product,
    var quantity: Int
) : Parcelable
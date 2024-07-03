package rees46.demo_android.entity.productsEntity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartProductEntity(
    val product: ProductEntity,
    var quantity: Int
) : Parcelable
package rees46.demo_android.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartProductDto(
    val product: ProductDto,
    var quantity: Int
) : Parcelable
package rees46.demo_android.features.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val producerName: String,
    val price: Int?,
    val priceFormatted: String,
    val priceFull: Int?,
    val priceFullFormatted: String?,
    val pictureUrl: String,
    val description: String
) : Parcelable
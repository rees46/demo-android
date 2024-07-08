package rees46.demo_android.data.products

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDto(
    val id: String,
    val name: String,
    val producerName: String,
    val price: Double?,
    val priceFormatted: String,
    val priceFull: Double?,
    val priceFullFormatted: String?,
    val pictureUrl: String,
    val description: String
) : Parcelable
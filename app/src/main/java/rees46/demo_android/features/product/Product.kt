package rees46.demo_android.features.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val producerName: String,
    val price: String,
    val salesRate: Int,
    val pictureUrl: String
) : Parcelable
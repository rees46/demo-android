package com.personaclick.demo_android.navigation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NavigationProduct(
    val id: String,
    val name: String,
    val producerName: String,
    val price: Double?,
    val priceFormatted: String,
    val priceFull: Double?,
    val priceFullFormatted: String?,
    val pictureUrl: String,
    val description: String,
    val rating: Float,
    val sale: Int
) : Parcelable

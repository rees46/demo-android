package rees46.demo_android.features.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(val productId: Int) : Parcelable
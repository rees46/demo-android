package rees46.demo_android.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryEntity(
    val id: String,
    val name: String,
    val parent: String,
    val url: String,
    val count: Int
) : Parcelable
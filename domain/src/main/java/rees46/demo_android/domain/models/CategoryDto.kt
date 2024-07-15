package rees46.demo_android.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryDto(
    val id: String,
    val name: String,
    val parent: String,
    val url: String,
    val count: Int
) : Parcelable
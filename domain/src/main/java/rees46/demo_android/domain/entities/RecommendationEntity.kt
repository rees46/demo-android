package rees46.demo_android.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationEntity(
    val title: String,
    val products: List<ProductEntity>
) : Parcelable
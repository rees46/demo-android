package rees46.demo_android.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationDto(
    val title: String,
    val products: List<ProductDto>
) : Parcelable
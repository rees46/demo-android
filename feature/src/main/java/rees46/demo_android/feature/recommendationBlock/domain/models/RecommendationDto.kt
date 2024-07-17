package rees46.demo_android.feature.recommendationBlock.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationDto(
    val title: String,
    val products: List<rees46.demo_android.feature.product.domain.models.ProductDto>
) : Parcelable
package rees46.demo_android.feature.recommendationBlock.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import rees46.demo_android.feature.product.domain.models.ProductDto

@Parcelize
data class RecommendationDto(
    val title: String,
    val products: List<ProductDto>
) : Parcelable

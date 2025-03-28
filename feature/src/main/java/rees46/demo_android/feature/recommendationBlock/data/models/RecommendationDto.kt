package personaclick.demo_android.feature.recommendationBlock.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import personaclick.demo_android.feature.productDetails.data.models.ProductDto

@Parcelize
data class RecommendationDto(
    val title: String,
    val products: List<ProductDto>
) : Parcelable

package personaclick.demo_android.feature.recommendationBlock.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import personaclick.demo_android.feature.productDetails.domain.models.Product

@Parcelize
data class Recommendation(
    val title: String,
    val products: List<Product>
) : Parcelable

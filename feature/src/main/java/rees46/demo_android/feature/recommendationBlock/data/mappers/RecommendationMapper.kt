package rees46.demo_android.feature.recommendationBlock.data.mappers

import rees46.demo_android.feature.productDetails.data.mappers.ProductMapper
import rees46.demo_android.feature.recommendationBlock.data.models.RecommendationDto
import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation

class RecommendationMapper  (
    private val productMapper: ProductMapper
) {
    fun toRecommendation(recommendationDto: RecommendationDto): Recommendation =
        with(recommendationDto) {
            Recommendation(
                title = title,
                products = productMapper.toProducts(products)
            )
        }
}

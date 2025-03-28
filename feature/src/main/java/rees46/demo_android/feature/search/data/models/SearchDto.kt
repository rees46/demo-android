package personaclick.demo_android.feature.search.data.models

import personaclick.demo_android.feature.productDetails.data.models.ProductDto

data class SearchDto(
    val products: List<ProductDto>,
    val categories: List<CategoryDto>
)

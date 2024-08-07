package rees46.demo_android.feature.search.data.models

import rees46.demo_android.feature.productDetails.data.models.ProductDto

data class SearchDto(
    val products: List<ProductDto>,
    val categories: List<CategoryDto>
)

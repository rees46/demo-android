package rees46.demo_android.feature.search.domain.models

import rees46.demo_android.feature.productDetails.domain.models.Product

data class Search(
    val products: List<Product>,
    val categories: List<Category>
)

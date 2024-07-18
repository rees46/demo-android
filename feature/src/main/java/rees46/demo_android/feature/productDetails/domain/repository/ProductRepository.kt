package rees46.demo_android.feature.productDetails.domain.repository

import rees46.demo_android.feature.productDetails.domain.models.ProductDto
import rees46.demo_android.feature.search.domain.models.CategoryDto

interface ProductRepository {

    fun searchProducts(
        query: String,
        onGetProducts: (List<ProductDto>) -> Unit,
        onGetCategories: (List<CategoryDto>) -> Unit
    )

    fun searchRecommendedProducts(
        onGetProducts: (List<ProductDto>) -> Unit
    )
}

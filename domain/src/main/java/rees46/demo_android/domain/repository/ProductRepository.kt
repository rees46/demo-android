package rees46.demo_android.domain.repository

import rees46.demo_android.domain.models.CategoryDto
import rees46.demo_android.domain.models.ProductDto

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

package rees46.demo_android.domain.repository

import rees46.demo_android.domain.entities.CategoryEntity
import rees46.demo_android.domain.entities.ProductEntity

interface ProductRepository {

    fun searchProducts(
        query: String,
        onGetProducts: (List<ProductEntity>) -> Unit,
        onGetCategories: (List<CategoryEntity>) -> Unit
    )

    fun searchRecommendedProducts(
        onGetProducts: (List<ProductEntity>) -> Unit
    )
}

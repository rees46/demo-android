package rees46.demo_android.feature.search.domain.repository

import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.search.domain.models.Category

interface SearchRepository {

    fun searchProducts(
        query: String,
        onGetProducts: (List<Product>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    )

    fun searchRecommendedProducts(
        onGetProducts: (List<Product>) -> Unit
    )
}

package rees46.demo_android.domain.repository

import com.personalizatio.api.responses.search.Category
import rees46.demo_android.domain.entities.ProductEntity

interface ProductRepository {

    fun searchProducts(
        query: String,
        onGetProducts: (List<ProductEntity>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    )

    fun searchRecommendedProducts(
        onGetProducts: (List<ProductEntity>) -> Unit
    )
}

package rees46.demo_android.domain.repository

import com.personalizatio.api.responses.product.Product
import com.personalizatio.api.responses.search.Category

interface ProductRepository {

    fun searchProducts(
        query: String,
        onGetProducts: (List<Product>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    )

    fun searchRecommendedProducts(
        onGetProducts: (List<Product>) -> Unit
    )
}

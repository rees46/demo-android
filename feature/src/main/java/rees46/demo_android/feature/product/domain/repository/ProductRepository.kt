package rees46.demo_android.feature.product.domain.repository

interface ProductRepository {

    fun searchProducts(
        query: String,
        onGetProducts: (List<rees46.demo_android.feature.product.domain.models.ProductDto>) -> Unit,
        onGetCategories: (List<rees46.demo_android.feature.search.domain.models.CategoryDto>) -> Unit
    )

    fun searchRecommendedProducts(
        onGetProducts: (List<rees46.demo_android.feature.product.domain.models.ProductDto>) -> Unit
    )
}

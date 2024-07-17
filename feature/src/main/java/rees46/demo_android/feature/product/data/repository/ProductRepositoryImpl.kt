package rees46.demo_android.feature.product.data.repository

import com.personalizatio.SDK
import rees46.demo_android.feature.product.data.utils.ProductUtils.toCategories
import rees46.demo_android.feature.product.data.utils.ProductUtils.toProducts
import rees46.demo_android.feature.product.domain.models.ProductDto
import rees46.demo_android.feature.product.domain.repository.ProductRepository
import rees46.demo_android.feature.search.domain.models.CategoryDto

class ProductRepositoryImpl (
    private val sdk: SDK
) : ProductRepository {

    override fun searchProducts(
        query: String,
        onGetProducts: (List<ProductDto>) -> Unit,
        onGetCategories: (List<CategoryDto>) -> Unit
    ) {
        sdk.searchManager.searchInstant(
            query = query,
            onSearchInstant = { searchInstantEntity ->
                onGetProducts.invoke(searchInstantEntity.products.toProducts())
                onGetCategories.invoke(searchInstantEntity.categories.toCategories())
            }
        )
    }

    override fun searchRecommendedProducts(
        onGetProducts: (List<ProductDto>) -> Unit
    ) {
        sdk.searchManager.searchBlank(
            onSearchBlank = { searchBlankEntity ->
                onGetProducts(searchBlankEntity.products.toProducts())
            }
        )
    }
}

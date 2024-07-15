package rees46.demo_android.data.repository.product

import com.personalizatio.SDK
import rees46.demo_android.data.utils.ProductUtils.toCategories
import rees46.demo_android.data.utils.ProductUtils.toProducts
import rees46.demo_android.domain.models.CategoryDto
import rees46.demo_android.domain.models.ProductDto
import rees46.demo_android.domain.repository.ProductRepository

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

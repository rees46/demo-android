package rees46.demo_android.data.repository.product

import com.personalizatio.SDK
import com.personalizatio.api.responses.search.Category
import rees46.demo_android.data.utils.ProductUtils.toProducts
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.repository.ProductRepository

class ProductRepositoryImpl (
    private val sdk: SDK
) : ProductRepository {

    override fun searchProducts(
        query: String,
        onGetProducts: (List<ProductEntity>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    ) {
        sdk.searchManager.searchInstant(
            query = query,
            onSearchInstant = { searchInstantEntity ->
                onGetProducts.invoke(searchInstantEntity.products.toProducts())
                onGetCategories.invoke(searchInstantEntity.categories)
            }
        )
    }

    override fun searchRecommendedProducts(
        onGetProducts: (List<ProductEntity>) -> Unit
    ) {
        sdk.searchManager.searchBlank(
            onSearchBlank = { searchBlankEntity ->
                onGetProducts(searchBlankEntity.products.toProducts())
            }
        )
    }
}

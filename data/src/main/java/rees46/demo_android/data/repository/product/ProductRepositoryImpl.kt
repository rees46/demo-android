package rees46.demo_android.data.repository.product

import com.personalizatio.SDK
import com.personalizatio.api.responses.product.Product
import com.personalizatio.api.responses.search.Category
import rees46.demo_android.domain.repository.ProductRepository

class ProductRepositoryImpl (
    private val sdk: SDK
) : ProductRepository {

    override fun searchProducts(
        query: String,
        onGetProducts: (List<Product>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    ) {
        sdk.searchManager.searchInstant(
            query = query,
            onSearchInstant = { searchInstantEntity ->
                onGetProducts.invoke(searchInstantEntity.products)
                onGetCategories.invoke(searchInstantEntity.categories)
            }
        )
    }

    override fun searchRecommendedProducts(
        onGetProducts: (List<Product>) -> Unit
    ) {
        sdk.searchManager.searchBlank(
            onSearchBlank = { searchBlankEntity ->
                onGetProducts(searchBlankEntity.products)
            }
        )
    }
}

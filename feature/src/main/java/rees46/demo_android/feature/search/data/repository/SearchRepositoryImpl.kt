package rees46.demo_android.feature.search.data.repository

import rees46.demo_android.feature.search.data.api.SearchApi
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.search.data.mappers.SearchMapper
import rees46.demo_android.feature.search.domain.repository.SearchRepository
import rees46.demo_android.feature.search.domain.models.Category

class SearchRepositoryImpl (
    private val productApi: SearchApi,
    private val searchMapper: SearchMapper
) : SearchRepository {

    override fun searchProducts(
        query: String,
        onGetProducts: (List<Product>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    ) {
        productApi.search(
            query = query,
            onSearch = { searchDto ->
                onGetProducts.invoke(searchMapper.toProducts(searchDto))
                onGetCategories.invoke(searchMapper.toCategories(searchDto))
            }
        )
    }

    override fun searchRecommendedProducts(
        onGetProducts: (List<Product>) -> Unit
    ) {
        productApi.searchBlank(
            onSearch = { searchDto ->
                onGetProducts(searchMapper.toProducts(searchDto))
            }
        )
    }
}

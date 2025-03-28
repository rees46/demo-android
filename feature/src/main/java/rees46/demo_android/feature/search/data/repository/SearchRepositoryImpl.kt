package personaclick.demo_android.feature.search.data.repository

import personaclick.demo_android.feature.search.data.api.SearchApi
import personaclick.demo_android.feature.search.data.mappers.SearchMapper
import personaclick.demo_android.feature.search.domain.repository.SearchRepository
import personaclick.demo_android.feature.search.domain.models.Search

class SearchRepositoryImpl (
    private val productApi: SearchApi,
    private val searchMapper: SearchMapper
) : SearchRepository {

    override fun searchProducts(
        query: String,
        onGetSearch: (Search) -> Unit
    ) {
        productApi.search(
            query = query,
            onSearch = { searchDto ->
                onGetSearch.invoke(searchMapper.toSearch(searchDto))
            }
        )
    }

    override fun searchRecommendedProducts(
        onGetSearch: (Search) -> Unit
    ) {
        productApi.searchBlank(
            onSearch = { searchDto ->
                onGetSearch(searchMapper.toSearch(searchDto))
            }
        )
    }
}

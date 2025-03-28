package personaclick.demo_android.feature.search.presentation.mappers

import personaclick.demo_android.feature.search.presentation.models.CategoryRecyclerViewItem
import personaclick.demo_android.feature.search.presentation.models.SearchRecyclerViewItem
import personaclick.demo_android.feature.products.presentation.mappers.ProductItemMapper
import personaclick.demo_android.feature.search.domain.models.Category
import personaclick.demo_android.feature.search.domain.models.Search

class SearchItemMapper(
    private val productItemMapper: ProductItemMapper
) {

    fun toSearchItem(search: Search) =
        SearchRecyclerViewItem(
            productItems = productItemMapper.toProductItems(search.products),
            categoryItems = toCategoryItems(search.categories)
        )

    private fun toCategoryItem(category: Category): CategoryRecyclerViewItem =
        with(category) {
            CategoryRecyclerViewItem(
                id = id,
                name = name,
                parent = parent,
                url = url,
                count = count
            )
        }

    private fun toCategoryItems(categories: Collection<Category>): List<CategoryRecyclerViewItem> =
        categories.map { toCategoryItem(it) }
}

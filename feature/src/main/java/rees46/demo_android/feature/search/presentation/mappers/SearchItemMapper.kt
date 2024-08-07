package rees46.demo_android.feature.search.presentation.mappers

import rees46.demo_android.feature.search.presentation.models.CategoryRecyclerViewItem
import rees46.demo_android.feature.search.presentation.models.SearchRecyclerViewItem
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper
import rees46.demo_android.feature.search.domain.models.Category
import rees46.demo_android.feature.search.domain.models.Search

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

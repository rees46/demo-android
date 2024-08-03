package rees46.demo_android.feature.search.presentation.mappers

import com.rees46.demo_android.ui.recyclerView.search.models.CategoryItem
import com.rees46.demo_android.ui.recyclerView.search.models.SearchItem
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper
import rees46.demo_android.feature.search.domain.models.Category
import rees46.demo_android.feature.search.domain.models.Search

class SearchItemMapper(
    private val productItemMapper: ProductItemMapper
) {

    fun toSearchItem(search: Search) =
        SearchItem(
            productItems = productItemMapper.toProductItems(search.products),
            categoryItems = toCategoryItems(search.categories)
        )

    private fun toCategoryItem(category: Category): CategoryItem =
        with(category) {
            CategoryItem(
                id = id,
                name = name,
                parent = parent,
                url = url,
                count = count
            )
        }

    private fun toCategoryItems(categories: Collection<Category>): List<CategoryItem> =
        categories.map { toCategoryItem(it) }
}

package rees46.demo_android.feature.search.presentation.mappers

import com.rees46.demo_android.ui.recyclerView.search.models.CategoryItem
import rees46.demo_android.feature.search.domain.models.Category

class SearchItemMapper {

    fun toCategoryItem(category: Category): CategoryItem =
        with(category) {
            CategoryItem(
                id = id,
                name = name,
                parent = parent,
                url = url,
                count = count
            )
        }

    fun toCategoryItems(categories: Collection<Category>): List<CategoryItem> =
        categories.map { toCategoryItem(it) }
}

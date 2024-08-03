package rees46.demo_android.feature.search.presentation.mappers

import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import com.rees46.demo_android.ui.recyclerView.search.models.CategoryItem
import com.rees46.demo_android.ui.recyclerView.search.models.SearchItem
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper
import rees46.demo_android.feature.search.domain.models.Category

class SearchItemMapper(
    private val productItemMapper: ProductItemMapper
) {

    fun toSearchItem(category: Category, product: Product) =
        SearchItem(
            categoryItem = toCategoryItem(category),
            productItem = productItemMapper.toProductItem(product)
        )

    fun toSearchItems(searches: Collection<SearchItem>): Collection<SearchItem> =
        searches.map { it }

    fun productToSearchItem(product: Product): SearchItem =
        SearchItem(
            categoryItem = CategoryItem("", "", "", "", 0),
            productItem = productItemMapper.toProductItem(product)
        )

    fun productsToSearchItems(products: Collection<Product>): List<SearchItem> =
        products.map { productToSearchItem(it) }

    fun categoriesToSearchItems(categories: Collection<Category>): List<SearchItem> =
        categories.map { categoryToSearchItem(it) }

    fun categoryToSearchItem(category: Category): SearchItem =
        SearchItem(
            categoryItem = toCategoryItem(category),
            productItem = ProductItem("", "Name", "", 0.0, "", 0.0, "", "", "", 0f)
        )

    fun toSearchItems(categories: List<Category>, products: List<Product>): Collection<SearchItem> {
        val searches = mutableListOf<SearchItem>()

        for (i in 0..categories.size) {
            searches.add(toSearchItem(
                product = products[i],
                category = categories[i]
            ))
        }

        return searches
    }

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

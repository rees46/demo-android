package personaclick.demo_android.feature.search.data.mappers

import personaclick.demo_android.feature.productDetails.data.mappers.ProductMapper
import personaclick.demo_android.feature.search.data.models.CategoryDto
import personaclick.demo_android.feature.search.data.models.SearchDto
import personaclick.demo_android.feature.search.domain.models.Category
import personaclick.demo_android.feature.search.domain.models.Search

class SearchMapper(
    private val productMapper: ProductMapper
) {

    fun toSearch(searchDto: SearchDto): Search =
        Search(
            products = productMapper.toProducts(searchDto.products),
            categories = toCategories(searchDto.categories)
        )

    private fun toCategory(categoryDto: CategoryDto): Category =
        with(categoryDto) {
            Category(
                id = id,
                name = name,
                parent = parent,
                url = url,
                count = count
            )
        }

    private fun toCategories(categoriesDto: List<CategoryDto>): List<Category> =
        categoriesDto.map { toCategory(it) }
}

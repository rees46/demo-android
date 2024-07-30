package rees46.demo_android.feature.search.data.mappers

import rees46.demo_android.feature.productDetails.data.mappers.ProductMapper
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.search.data.models.CategoryDto
import rees46.demo_android.feature.search.data.models.SearchDto
import rees46.demo_android.feature.search.domain.models.Category

class SearchMapper(
    private val productMapper: ProductMapper
) {

    fun toCategories(searchDto: SearchDto): List<Category> =
        searchDto.categories.map { toCategory(it) }

    fun toProducts(searchDto: SearchDto): List<Product> =
        productMapper.toProducts(searchDto.products)

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
}

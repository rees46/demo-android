package rees46.demo_android.feature.search.data.api

import com.personalizatio.SDK
import com.personalizatio.api.responses.product.Product
import com.personalizatio.api.responses.search.Category
import rees46.demo_android.feature.productDetails.data.models.ProductDto
import rees46.demo_android.feature.search.data.models.SearchDto
import rees46.demo_android.feature.search.data.models.CategoryDto
import kotlin.random.Random

class SearchApi(
    private val sdk: SDK
) {

    fun search(
        query: String,
        onSearch: (SearchDto) -> Unit
    ) {
        sdk.searchManager.searchInstant(
            query = query,
            onSearchInstant = { searchInstantEntity ->
                val searchDto = SearchDto(
                    products = searchInstantEntity.products.toProducts(),
                    categories = searchInstantEntity.categories.toCategories()
                )
                onSearch.invoke(searchDto)
            }
        )
    }

    fun searchBlank(onSearch: (SearchDto) -> Unit) {
        sdk.searchManager.searchBlank(
            onSearchBlank = { searchBlankEntity ->
                val searchDto = SearchDto(
                    products = searchBlankEntity.products.toProducts(),
                    categories = listOf()
                )
                onSearch.invoke(searchDto)
            }
        )
    }
}

fun Product.toProduct(): ProductDto {
    return ProductDto(
        id = id,
        name = name,
        producerName = brand,
        price = price,
        priceFormatted = priceFormatted,
        priceFull = priceFull,
        priceFullFormatted = priceFullFormatted,
        pictureUrl = picture,
        description = description,
        rating = getRating()
    )
}

fun List<Product>.toProducts(): List<ProductDto> =
    map { it.toProduct() }

// TODO: replaced by real data
private fun getRating() =
    Random.nextFloat() * 5

private fun Category.toCategory(): CategoryDto {
    return CategoryDto(
        id = id,
        name = name,
        parent = parent,
        url = url,
        count = count
    )
}

fun List<Category>.toCategories(): List<CategoryDto> =
    map { it.toCategory() }

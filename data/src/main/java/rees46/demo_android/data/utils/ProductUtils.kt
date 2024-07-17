package rees46.demo_android.data.utils

import com.personalizatio.api.responses.product.Product
import com.personalizatio.api.responses.search.Category
import rees46.demo_android.domain.models.CategoryDto
import rees46.demo_android.domain.models.ProductDto
import kotlin.random.Random

object ProductUtils {

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

    fun Category.toCategory(): CategoryDto {
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

    // TODO: replaced by real data
    private fun getRating() =
        Random.nextFloat() * 5
}

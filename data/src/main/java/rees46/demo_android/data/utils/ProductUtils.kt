package rees46.demo_android.data.utils

import com.personalizatio.api.responses.product.Product
import com.personalizatio.api.responses.search.Category
import rees46.demo_android.domain.entities.CategoryEntity
import rees46.demo_android.domain.entities.ProductEntity

object ProductUtils {

    fun Product.toProduct(): ProductEntity {
        return ProductEntity(
            id = id,
            name = name,
            producerName = brand,
            price = price,
            priceFormatted = priceFormatted,
            priceFull = priceFull,
            priceFullFormatted = priceFullFormatted,
            pictureUrl = picture,
            description = description,
        )
    }

    fun List<Product>.toProducts(): List<ProductEntity> =
        map { it.toProduct() }

    fun Category.toCategory(): CategoryEntity {
        return CategoryEntity(
            id = id,
            name = name,
            parent = parent,
            url = url,
            count = count
        )
    }

    fun List<Category>.toCategories(): List<CategoryEntity> =
        map { it.toCategory() }
}

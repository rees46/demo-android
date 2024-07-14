package rees46.demo_android.data.utils

import com.personalizatio.api.responses.product.Product
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

    fun List<Product>.toProducts(): MutableList<ProductEntity> =
        map { it.toProduct() }.toMutableList()
}

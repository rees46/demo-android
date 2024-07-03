package rees46.demo_android.features.product

import com.personalizatio.api.entities.product.ProductEntity

object ProductUtils {

    internal fun createProduct(productEntity: ProductEntity) : Product {
        return Product(
            id = productEntity.id,
            name = productEntity.name,
            producerName = productEntity.brand,
            price = productEntity.price,
            priceFormatted = productEntity.priceFormatted,
            priceFull = productEntity.priceFull,
            priceFullFormatted = productEntity.priceFullFormatted,
            pictureUrl = productEntity.picture,
            description = productEntity.description,
        )
    }
}

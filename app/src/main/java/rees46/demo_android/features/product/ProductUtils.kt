package rees46.demo_android.features.product

import com.personalizatio.entities.products.productInfo.ProductInfoEntity

object ProductUtils {

    internal fun createProduct(productInfoEntity: ProductInfoEntity) : Product {
        return Product(
            id = productInfoEntity.uniqid,
            name = productInfoEntity.name,
            producerName = productInfoEntity.brand,
            price = productInfoEntity.price.toDouble(),
            priceFormatted = productInfoEntity.price,
            priceFull = null,
            priceFullFormatted = null,
            pictureUrl = productInfoEntity.picture,
            description = productInfoEntity.description,
        )
    }

    internal fun createProduct(product: com.personalizatio.entities.products.Product) : Product {
        return Product(
            id = product.id,
            name = product.name,
            producerName = product.brand,
            price = product.price,
            priceFormatted = product.priceFormatted,
            priceFull = product.priceFull,
            priceFullFormatted = product.priceFullFormatted,
            pictureUrl = product.picture,
            description = product.description,
        )
    }
}

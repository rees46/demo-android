package rees46.demo_android.features.product

import com.personalizatio.entities.products.productInfo.ProductInfoEntity
import com.personalizatio.entities.recommended.Recommend

object ProductUtils {

    internal fun createProduct(productInfoEntity: ProductInfoEntity) : Product {
        return Product(
            id = productInfoEntity.uniqid,
            name = productInfoEntity.name,
            producerName = productInfoEntity.brand,
            price = Integer.getInteger(productInfoEntity.price),
            priceFormatted = productInfoEntity.price,
            priceFull = null,
            priceFullFormatted = null,
            pictureUrl = productInfoEntity.picture,
            description = productInfoEntity.description,
        )
    }

    internal fun createProduct(recommend: Recommend) : Product {
        return Product(
            id = recommend.id,
            name = recommend.name,
            producerName = recommend.brand,
            price = recommend.price,
            priceFormatted = recommend.priceFormatted,
            priceFull = recommend.priceFull,
            priceFullFormatted = recommend.priceFullFormatted,
            pictureUrl = recommend.picture,
            description = recommend.description,
        )
    }
}

package rees46.demo_android.features.product

import com.personalizatio.api.entities.product.ProductEntity

fun ProductEntity.createProduct(): rees46.demo_android.entity.productsEntity.ProductEntity {
    return rees46.demo_android.entity.productsEntity.ProductEntity(
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

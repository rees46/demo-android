package rees46.demo_android.features.product

import com.personalizatio.api.entities.product.ProductEntity

fun ProductEntity.createProduct(): rees46.demo_android.entities.products.ProductEntity {
    return rees46.demo_android.entities.products.ProductEntity(
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

fun List<ProductEntity>.createProductList(): MutableList<rees46.demo_android.entities.products.ProductEntity> =
    map { it.createProduct() }.toMutableList()

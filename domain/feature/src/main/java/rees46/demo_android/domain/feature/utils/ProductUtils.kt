package rees46.demo_android.domain.feature.utils

import com.personalizatio.api.entities.product.ProductEntity
import rees46.demo_android.data.products.ProductDto

fun ProductEntity.createProduct(): ProductDto {
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
    )
}

fun List<ProductEntity>.createProductList(): MutableList<ProductDto> =
    map { it.createProduct() }.toMutableList()

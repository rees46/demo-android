package rees46.demo_android.domain.feature.utils

import com.personalizatio.api.responses.product.Product
import rees46.demo_android.domain.entities.ProductDto

fun Product.createProduct(): ProductDto {
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

fun List<Product>.createProductList(): MutableList<ProductDto> =
    map { it.createProduct() }.toMutableList()

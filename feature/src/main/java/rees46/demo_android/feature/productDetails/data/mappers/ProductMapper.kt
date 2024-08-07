package rees46.demo_android.feature.productDetails.data.mappers

import rees46.demo_android.feature.productDetails.data.models.ProductDto
import rees46.demo_android.feature.productDetails.domain.models.Product

class ProductMapper {

    fun toProduct(productDto: ProductDto): Product =
        with(productDto) {
            Product(
                id = id,
                name = name,
                producerName = producerName,
                price = price,
                priceFormatted = priceFormatted,
                priceFull = priceFull,
                priceFullFormatted = priceFullFormatted,
                pictureUrl = pictureUrl,
                description = description,
                rating = rating,
                sale = sale
            )
        }

    fun toProducts(productDtoList: List<ProductDto>): List<Product> =
        productDtoList.map { toProduct(it) }
}

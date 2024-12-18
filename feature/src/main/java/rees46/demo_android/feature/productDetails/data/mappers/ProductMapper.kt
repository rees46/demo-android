package rees46.demo_android.feature.productDetails.data.mappers

import rees46.demo_android.feature.productDetails.data.models.ProductDto
import rees46.demo_android.feature.productDetails.domain.models.Product

class ProductMapper {

    fun toProducts(productDtoList: List<ProductDto>): List<Product> {
        return productDtoList.map {
            with(it) {
                Product(
                    id = id,
                    name = name,
                    producerName = producerName.orEmpty(),
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
        }
    }
}

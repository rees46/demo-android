package rees46.demo_android.feature.products.presentation.mappers

import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.products.presentation.adapter.ProductItem

class ProductItemMapper {

    fun toProductItem(product: Product): ProductItem =
        with(product) {
            ProductItem(
                id = id,
                name = name,
                producerName = producerName,
                price = price,
                priceFormatted = priceFormatted,
                priceFull = priceFull,
                priceFullFormatted = priceFullFormatted,
                pictureUrl = pictureUrl,
                description = description,
                rating = rating
            )
        }

    fun toProductItems(products: Collection<Product>): List<ProductItem> =
        products.map { toProductItem(it) }

    fun toProduct(productItem: ProductItem): Product =
        with(productItem) {
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
                rating = rating
            )
        }

    fun toProducts(productItems: Collection<ProductItem>): List<Product> =
        productItems.map { toProduct(it) }
}

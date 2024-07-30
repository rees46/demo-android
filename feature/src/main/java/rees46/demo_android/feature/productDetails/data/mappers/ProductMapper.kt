package rees46.demo_android.feature.productDetails.data.mappers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import rees46.demo_android.feature.productDetails.data.models.ProductDto
import rees46.demo_android.feature.productDetails.domain.models.Product
import java.net.URL

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
                picture = getPicture(pictureUrl),
                description = description,
                rating = rating
            )
        }

    fun toProducts(productDtoList: List<ProductDto>): List<Product> =
        productDtoList.map { toProduct(it) }

    private fun getPicture(urlString: String): Bitmap =
        with(Dispatchers.IO) {
            BitmapFactory.decodeStream(URL(urlString).openConnection().getInputStream())
        }
}

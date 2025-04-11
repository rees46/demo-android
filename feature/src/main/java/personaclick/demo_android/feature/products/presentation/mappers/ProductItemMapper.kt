package personaclick.demo_android.feature.products.presentation.mappers

import personaclick.demo_android.feature.productDetails.domain.models.Product
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem

class ProductItemMapper {

    fun toProductItem(product: Product): ProductRecyclerViewItem =
        with(product) {
            ProductRecyclerViewItem(
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

    fun toProductItems(products: Collection<Product>): List<ProductRecyclerViewItem> =
        products.map { toProductItem(it) }

    fun toProduct(productItem: ProductRecyclerViewItem): Product =
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
                rating = rating,
                sale = sale
            )
        }

    fun toProducts(productItems: Collection<ProductRecyclerViewItem>): List<Product> =
        productItems.map { toProduct(it) }
}

package personaclick.demo_android.feature.productDetails.domain.mappers

import com.personaclick.demo_android.navigation.models.NavigationProduct
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import personaclick.demo_android.feature.productDetails.domain.models.Product

class NavigationProductMapper {

    fun toNavigationProduct(product: Product): NavigationProduct =
        with(product) {
            NavigationProduct(
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

    fun toNavigationProducts(productList: List<Product>): List<NavigationProduct> =
        productList.map { toNavigationProduct(it) }

    fun toNavigationProduct(productItem: ProductRecyclerViewItem): NavigationProduct =
        with(productItem) {
            NavigationProduct(
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

    fun toProduct(navigationProduct: NavigationProduct?): Product? {
        if (navigationProduct == null) return null

        with(navigationProduct) {
            return Product(
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
    }

    fun toProductItem(navigationProduct: NavigationProduct): ProductRecyclerViewItem =
        with(navigationProduct) {
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

    fun toProductItems(navigationProducts: Collection<NavigationProduct>): List<ProductRecyclerViewItem> =
        navigationProducts.map { toProductItem(it) }
}

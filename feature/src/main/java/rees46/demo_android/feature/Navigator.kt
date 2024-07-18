package rees46.demo_android.feature

import rees46.demo_android.feature.productDetails.domain.models.Product

interface Navigator {
    fun navigate(destination: Destination)

    fun navigate(id: Int)
}

sealed interface Destination
class ProductDetails(val product: Product): Destination
class ProductsDetails(val products: Collection<Product>): Destination
package rees46.demo_android.feature.search.data.repository

import rees46.demo_android.feature.search.data.api.SearchApi
import rees46.demo_android.feature.productDetails.data.models.ProductDto
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.search.domain.repository.SearchRepository
import rees46.demo_android.feature.search.data.models.CategoryDto
import rees46.demo_android.feature.search.domain.models.Category

class SearchRepositoryImpl (
    private val productApi: SearchApi
) : SearchRepository {

    override fun searchProducts(
        query: String,
        onGetProducts: (List<Product>) -> Unit,
        onGetCategories: (List<Category>) -> Unit
    ) {
        productApi.search(
            query = query,
            onSearch = { searchDto ->
                onGetProducts.invoke(searchDto.products.toProducts())
                onGetCategories.invoke(searchDto.categories.toCategories())
            }
        )
    }

    override fun searchRecommendedProducts(
        onGetProducts: (List<Product>) -> Unit
    ) {
        productApi.searchBlank(
            onSearch = { searchDto ->
                onGetProducts(searchDto.products.toProducts())
            }
        )
    }

    companion object {
        fun ProductDto.toProduct(): Product {
            return Product(
                id = this.id,
                name = this.name,
                producerName = this.producerName,
                price = this.price,
                priceFormatted = this.priceFormatted,
                priceFull = this.priceFull,
                priceFullFormatted = this.priceFullFormatted,
                pictureUrl = this.pictureUrl,
                description = this.description,
                rating = this.rating
            )
        }

        fun List<ProductDto>.toProducts(): List<Product> =
            map { it.toProduct() }
    }

    private fun CategoryDto.toCategory(): Category {
        return Category(
            id = id,
            name = name,
            parent = parent,
            url = url,
            count = count
        )
    }

    private fun List<CategoryDto>.toCategories(): List<Category> =
        map { it.toCategory() }
}

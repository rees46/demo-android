package rees46.demo_android.domain.features.cardProduct.usecase

import rees46.demo_android.domain.models.ProductDto
import rees46.demo_android.domain.repository.CartRepository

class AddProductToCartUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(product: ProductDto, quantity: Int) {
        cartRepository.addProduct(product, quantity)
    }
}

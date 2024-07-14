package rees46.demo_android.domain.usecase.cart

import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.domain.repository.CartRepository

class AddProductToCartUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(product: ProductEntity, quantity: Int) {
        cartRepository.addProduct(product, quantity)
    }
}

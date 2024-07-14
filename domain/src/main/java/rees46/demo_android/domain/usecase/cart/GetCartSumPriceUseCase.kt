package rees46.demo_android.domain.usecase.cart

import kotlinx.coroutines.flow.MutableSharedFlow
import rees46.demo_android.domain.repository.CartRepository

class GetCartSumPriceUseCase (
    private val cartRepository: CartRepository
) {

    operator fun invoke(): MutableSharedFlow<Double> =
        cartRepository.getSumPrice()
}

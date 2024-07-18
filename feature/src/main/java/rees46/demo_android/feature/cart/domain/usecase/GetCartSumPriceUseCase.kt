package rees46.demo_android.feature.cart.domain.usecase

import kotlinx.coroutines.flow.MutableSharedFlow
import rees46.demo_android.feature.cart.domain.repository.CartRepository

class GetCartSumPriceUseCase (
    private val cartRepository: CartRepository
) {

    fun execute(): MutableSharedFlow<Double> =
        cartRepository.getSumPrice()
}

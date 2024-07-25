package rees46.demo_android.feature.cart.domain.usecase

import kotlinx.coroutines.flow.Flow
import rees46.demo_android.feature.cart.domain.repository.CartRepository

class GetCartSumPriceUseCase (
    private val cartRepository: CartRepository
) {

    fun execute(): Flow<Double> =
        cartRepository.getSumPrice()
}

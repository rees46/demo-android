package personaclick.demo_android.feature.cart.domain.usecase

import kotlinx.coroutines.flow.StateFlow
import personaclick.demo_android.feature.cart.domain.repository.CartRepository

class GetCartSumPriceUseCase (
    private val cartRepository: CartRepository
) {

    fun invoke(): StateFlow<Double> =
        cartRepository.getSumPrice()
}

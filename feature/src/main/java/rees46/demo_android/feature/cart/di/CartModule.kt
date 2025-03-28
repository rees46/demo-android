package personaclick.demo_android.feature.cart.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import personaclick.demo_android.feature.cart.data.api.CartApi
import personaclick.demo_android.feature.cart.data.models.Cart
import personaclick.demo_android.feature.cart.data.repository.CartRepositoryImpl
import personaclick.demo_android.feature.cart.domain.repository.CartRepository
import personaclick.demo_android.feature.cart.domain.usecase.GetCartProductsUseCase
import personaclick.demo_android.feature.cart.domain.usecase.GetCartSumPriceUseCase
import personaclick.demo_android.feature.cart.domain.usecase.RemoveProductFromCartUseCase
import personaclick.demo_android.feature.cart.presentation.mappers.CartProductItemMapper
import personaclick.demo_android.feature.cart.presentation.viewmodel.CartViewModel

val cartModule = module {
    viewModel {
        CartViewModel(
            getCartProductsUseCase = get(),
            removeProductFromCartUseCase = get(),
            getCartSumPriceUseCase = get(),
            getRecommendationUseCase = get()
        )
    }
    single {
        CartProductItemMapper(
            productItemMapper = get()
        )
    }
    single {
        GetCartProductsUseCase(
            cartRepository = get()
        )
    }
    single {
        GetCartSumPriceUseCase(
            cartRepository = get()
        )
    }
    single {
        RemoveProductFromCartUseCase(
            cartRepository = get()
        )
    }
    single<CartApi> {
        CartApi(
            sdk = get()
        )
    }
    single<Cart> {
        Cart()
    }
    single<CartRepository> {
        CartRepositoryImpl(
            cartApi = get(),
            cart = get()
        )
    }
}

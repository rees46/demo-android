package rees46.demo_android.feature.cart.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.cart.data.repository.CartRepositoryImpl
import rees46.demo_android.feature.cart.domain.repository.CartRepository
import rees46.demo_android.feature.cart.domain.usecase.GetCartProductsUseCase
import rees46.demo_android.feature.cart.domain.usecase.GetCartSumPriceUseCase
import rees46.demo_android.feature.cart.domain.usecase.RemoveProductFromCartUseCase
import rees46.demo_android.feature.cart.presentation.viewmodel.CartViewModel

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
    single<CartRepository> {
        CartRepositoryImpl(
            sdk = get(),
            cart = rees46.demo_android.feature.cart.data.models.Cart()
        )
    }
}
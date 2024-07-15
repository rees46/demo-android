package rees46.demo_android.domain.features.cart.di

import org.koin.dsl.module
import rees46.demo_android.domain.features.cart.usecase.GetCartProductsUseCase
import rees46.demo_android.domain.features.cart.usecase.GetCartSumPriceUseCase
import rees46.demo_android.domain.features.cart.usecase.RemoveProductFromCartUseCase

val cartUseCaseModule = module {
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
}

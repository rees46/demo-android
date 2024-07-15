package rees46.demo_android.domain.features.cardProduct.di

import org.koin.dsl.module
import rees46.demo_android.domain.features.cardProduct.usecase.AddProductToCartUseCase
import rees46.demo_android.domain.features.cardProduct.usecase.GetCartProductUseCase
import rees46.demo_android.domain.features.cardProduct.usecase.GetRecommendationForProductUseCase

val cardProductUseCaseModule = module {
    single {
        AddProductToCartUseCase(
            cartRepository = get()
        )
    }
    single {
        GetCartProductUseCase(
            cartRepository = get()
        )
    }
    single {
        GetRecommendationForProductUseCase(
            recommendationRepository = get()
        )
    }
}

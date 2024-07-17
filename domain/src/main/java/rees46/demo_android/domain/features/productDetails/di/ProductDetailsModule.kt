package rees46.demo_android.domain.features.productDetails.di

import org.koin.dsl.module
import rees46.demo_android.domain.features.productDetails.usecase.AddProductToCartUseCase
import rees46.demo_android.domain.features.productDetails.usecase.GetCartProductUseCase
import rees46.demo_android.domain.features.productDetails.usecase.GetRecommendationForProductUseCase

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

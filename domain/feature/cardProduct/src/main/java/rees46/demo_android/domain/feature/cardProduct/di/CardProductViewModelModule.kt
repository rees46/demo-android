package rees46.demo_android.domain.feature.cardProduct.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.domain.feature.cardProduct.presentation.CardProductViewModel

val cardProductViewModelModule = module {
    viewModel { parameter ->
        CardProductViewModel(
            sdk = get(),
            addProductToCartUseCase = get(),
            getCartProductUseCase = get(),
            getRecommendationForProductUseCase = get(),
            product = parameter.get()
        )
    }
}

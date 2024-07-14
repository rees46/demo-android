package rees46.demo_android.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.presentation.ui.cart.CartViewModel

object ViewModelModule {

    val module = module {
        viewModel {
            CartViewModel(
                getCartProductsUseCase = get(),
                removeProductFromCartUseCase = get(),
                getCartSumPriceUseCase = get(),
                getRecommendationUseCase = get()
            )
        }
    }
}

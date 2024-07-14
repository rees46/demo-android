package rees46.demo_android.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.presentation.ui.cardProduct.CardProductViewModel
import rees46.demo_android.presentation.ui.cart.CartViewModel
import rees46.demo_android.presentation.ui.home.HomeViewModel

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
        viewModel {
            HomeViewModel(
                getRecommendationUseCase = get()
            )
        }
        viewModel { product ->
            CardProductViewModel(
                addProductToCartUseCase = get(),
                getCartProductUseCase = get(),
                getRecommendationForProductUseCase = get(),
                product = product.get()
            )
        }
    }
}

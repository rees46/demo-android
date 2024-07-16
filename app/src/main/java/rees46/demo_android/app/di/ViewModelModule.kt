package rees46.demo_android.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.app.ui.cardProduct.CardProductViewModel
import rees46.demo_android.app.ui.cart.CartViewModel
import rees46.demo_android.app.ui.home.HomeViewModel
import rees46.demo_android.app.ui.main.MainViewModel
import rees46.demo_android.app.ui.products.ProductsViewModel
import rees46.demo_android.app.ui.settings.SettingsViewModel

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
        viewModel {
            MainViewModel(
                searchProductsUseCase = get(),
                searchRecommendedProductsUseCase = get()
            )
        }
        viewModel {
            ProductsViewModel()
        }
        viewModel {
            SettingsViewModel()
        }
    }
}

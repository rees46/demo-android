package rees46.demo_android.domain.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.domain.features.productDetails.presentation.ProductDetailsViewModel
import rees46.demo_android.domain.features.cart.presentation.CartViewModel
import rees46.demo_android.domain.features.home.presentation.HomeViewModel
import rees46.demo_android.domain.features.search.presentation.SearchViewModel
import rees46.demo_android.domain.features.products.presentation.ProductsViewModel
import rees46.demo_android.domain.features.settings.presentation.SettingsViewModel

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
            ProductDetailsViewModel(
                addProductToCartUseCase = get(),
                getCartProductUseCase = get(),
                getRecommendationForProductUseCase = get(),
                product = product.get()
            )
        }
        viewModel {
            SearchViewModel(
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

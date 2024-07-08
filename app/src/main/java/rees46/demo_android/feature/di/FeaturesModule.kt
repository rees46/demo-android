package rees46.demo_android.feature.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.card_product.presentation.CardProductViewModel
import rees46.demo_android.feature.main.cart.presentation.CartViewModel
import rees46.demo_android.feature.main.home.presentation.HomeViewModel
import rees46.demo_android.feature.main.presentation.MainViewModel

object FeaturesModule {

    val homeFragmentModule = module {
        viewModel { HomeViewModel(sdk = get()) }
    }

    val cardProductFragmentModule = module {
        viewModel { parameter ->
            CardProductViewModel(
                sdk = get(),
                product = parameter.get()
            )
        }
    }

    val cartFragmentModule = module {
        viewModel { CartViewModel(sdk = get()) }
    }

    val mainViewModel = module {
        viewModel { MainViewModel(sdk = get()) }
    }
}
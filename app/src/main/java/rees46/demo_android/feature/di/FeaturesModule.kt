package rees46.demo_android.feature.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.cardProduct.CardProductViewModel

object FeaturesModule {

    val homeFragmentModule = module {
        viewModel { rees46.demo_android.feature.main.home.presentation.HomeViewModel(sdk = get()) }
    }

    val cardProductFragmentModule = module {
        viewModel { parameter -> CardProductViewModel(sdk = get(), args = parameter.get()) }
    }

    val cartFragmentModule = module {
        viewModel { rees46.demo_android.feature.main.cart.presentation.CartViewModel(sdk = get()) }
    }

    val mainViewModel = module {
        viewModel { rees46.demo_android.feature.main.presentation.MainViewModel(get()) }
    }
}
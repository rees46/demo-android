package rees46.demo_android.features.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.features.cardProduct.CardProductViewModel
import rees46.demo_android.features.main.MainViewModel
import rees46.demo_android.features.main.cart.CartFragment
import rees46.demo_android.features.main.cart.CartViewModel
import rees46.demo_android.features.main.home.HomeViewModel

object FeaturesModule {

    val homeFragmentModule = module {
        viewModel { HomeViewModel(sdk = get()) }
    }

    val cardProductFragmentModule = module {
        viewModel { parameter -> CardProductViewModel(sdk = get(), args = parameter.get()) }
    }

    val cartFragmentModule = module {
        viewModel { CartViewModel(sdk = get()) }
    }

    val mainViewModel = module {
        viewModel { MainViewModel(get()) }
    }
}
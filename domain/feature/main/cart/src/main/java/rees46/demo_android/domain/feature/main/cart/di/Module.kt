package rees46.demo_android.domain.feature.main.cart.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.domain.feature.main.cart.presentation.CartViewModel

object Module {

    val cartFragmentModule = module {
        viewModel { CartViewModel(sdk = get()) }
    }
}

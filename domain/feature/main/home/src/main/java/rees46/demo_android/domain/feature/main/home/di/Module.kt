package rees46.demo_android.domain.feature.main.home.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.domain.feature.main.home.presentation.HomeViewModel

object Module {

    val homeFragmentModule = module {
        viewModel { HomeViewModel(sdk = get()) }
    }
}

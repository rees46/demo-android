package rees46.demo_android.feature.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.main.presentation.MainViewModel

object Module {

    val mainFragmentModule = module {
        viewModel { MainViewModel(sdk = get()) }
    }
}

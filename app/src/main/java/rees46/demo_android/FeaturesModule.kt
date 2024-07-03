package rees46.demo_android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.features.main.MainViewModel

object FeaturesModule {

    val module = module {
        viewModel { MainViewModel(get()) }
    }
}
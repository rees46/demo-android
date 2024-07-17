package rees46.demo_android.feature.products.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.products.presentation.viewmodel.ProductsViewModel

val productsModule = module {
    viewModel {
        ProductsViewModel()
    }
}

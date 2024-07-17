package rees46.demo_android.feature.search.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.search.presentation.viewmodel.SearchViewModel
import rees46.demo_android.feature.search.domain.usecase.SearchProductsUseCase
import rees46.demo_android.feature.search.domain.usecase.SearchRecommendedProductsUseCase

val searchModule = module {
    viewModel {
        SearchViewModel(
            searchProductsUseCase = get(),
            searchRecommendedProductsUseCase = get()
        )
    }
    single {
        SearchProductsUseCase(
            productRepository = get()
        )
    }
    single {
        SearchRecommendedProductsUseCase(
            productRepository = get()
        )
    }
}

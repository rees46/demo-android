package rees46.demo_android.domain.features.search.di

import org.koin.dsl.module
import rees46.demo_android.domain.features.search.usecase.SearchProductsUseCase
import rees46.demo_android.domain.features.search.usecase.SearchRecommendedProductsUseCase

val searchUseCaseModule = module {
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

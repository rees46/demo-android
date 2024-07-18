package rees46.demo_android.feature.search.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.search.data.api.SearchApi
import rees46.demo_android.feature.search.data.repository.SearchRepositoryImpl
import rees46.demo_android.feature.search.domain.repository.SearchRepository
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
    single<SearchApi> {
        SearchApi(
            sdk = get()
        )
    }
    single<SearchRepository> {
        SearchRepositoryImpl(
            productApi = get()
        )
    }
    single {
        SearchProductsUseCase(
            searchRepository = get()
        )
    }
    single {
        SearchRecommendedProductsUseCase(
            searchRepository = get()
        )
    }
}

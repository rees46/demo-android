package personaclick.demo_android.feature.search.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import personaclick.demo_android.feature.search.data.api.SearchApi
import personaclick.demo_android.feature.search.data.mappers.SearchMapper
import personaclick.demo_android.feature.search.data.repository.SearchRepositoryImpl
import personaclick.demo_android.feature.search.domain.repository.SearchRepository
import personaclick.demo_android.feature.search.presentation.viewmodel.SearchViewModel
import personaclick.demo_android.feature.search.domain.usecase.SearchProductsUseCase
import personaclick.demo_android.feature.search.domain.usecase.SearchRecommendedProductsUseCase
import personaclick.demo_android.feature.search.presentation.mappers.SearchItemMapper

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
    single {
        SearchMapper(
            productMapper = get()
        )
    }
    single {
        SearchItemMapper(
            productItemMapper = get()
        )
    }
    single<SearchRepository> {
        SearchRepositoryImpl(
            productApi = get(),
            searchMapper = get()
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

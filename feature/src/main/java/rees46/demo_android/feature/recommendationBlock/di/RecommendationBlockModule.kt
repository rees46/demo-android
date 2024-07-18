package rees46.demo_android.feature.recommendationBlock.di

import org.koin.dsl.module
import rees46.demo_android.feature.recommendationBlock.data.api.RecommendationApi
import rees46.demo_android.feature.recommendationBlock.data.repository.RecommendationRepositoryImpl
import rees46.demo_android.feature.recommendationBlock.domain.repository.RecommendationRepository
import rees46.demo_android.feature.recommendationBlock.domain.usecase.GetRecommendationUseCase

val recommendationBlockModule = module {
    single {
        GetRecommendationUseCase(
            recommendationRepository = get()
        )
    }
    single<RecommendationApi> {
        RecommendationApi(
            sdk = get()
        )
    }
    single<RecommendationRepository> {
        RecommendationRepositoryImpl(
            recommendationApi = get()
        )
    }
}

package rees46.demo_android.domain.features.recommendation.di

import org.koin.dsl.module
import rees46.demo_android.domain.features.recommendation.usecase.GetRecommendationUseCase

val recommendationUseCaseModule = module {
    single {
        GetRecommendationUseCase(
            recommendationRepository = get()
        )
    }
}

package personaclick.demo_android.feature.home.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import personaclick.demo_android.core.settings.RecommendationSettings
import personaclick.demo_android.feature.home.presentation.viewmodel.HomeViewModel

val homeModule = module {
    viewModel {
        HomeViewModel(
            getRecommendationUseCase = get(),
            recommenderCode = RecommendationSettings.SIMPLE_RECOMMENDED_CODE
        )
    }
}

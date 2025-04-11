package personaclick.demo_android.feature.settings.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import personaclick.demo_android.feature.category.presentation.viewmodel.CategoryViewModel

val settingsModule = module {
    viewModel {
        CategoryViewModel()
    }
}

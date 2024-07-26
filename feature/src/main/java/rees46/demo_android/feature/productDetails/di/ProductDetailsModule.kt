package rees46.demo_android.feature.productDetails.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.core.settings.RecommendationSettings
import rees46.demo_android.feature.productDetails.domain.usecase.AddProductToCartUseCase
import rees46.demo_android.feature.productDetails.domain.usecase.GetCartProductUseCase
import rees46.demo_android.feature.productDetails.domain.usecase.GetRecommendationForProductUseCase
import rees46.demo_android.feature.productDetails.presentation.viewmodel.ProductDetailsViewModel

val productDetailsModule = module {
    viewModel { product ->
        ProductDetailsViewModel(
            addProductToCartUseCase = get(),
            getCartProductUseCase = get(),
            getRecommendationForProductUseCase = get(),
            recommendedCode = RecommendationSettings.ALSO_LIKE_RECOMMENDED_CODE,
            product = product.get()
        )
    }
    single {
        AddProductToCartUseCase(
            cartRepository = get()
        )
    }
    single {
        GetCartProductUseCase(
            cartRepository = get()
        )
    }
    single {
        GetRecommendationForProductUseCase(
            recommendationRepository = get()
        )
    }
}

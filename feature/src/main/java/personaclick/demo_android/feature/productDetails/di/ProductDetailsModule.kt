package personaclick.demo_android.feature.productDetails.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import personaclick.demo_android.core.settings.RecommendationSettings
import personaclick.demo_android.feature.productDetails.data.mappers.ProductMapper
import personaclick.demo_android.feature.productDetails.domain.mappers.NavigationProductMapper
import personaclick.demo_android.feature.productDetails.domain.usecase.AddProductToCartUseCase
import personaclick.demo_android.feature.productDetails.domain.usecase.GetCartProductUseCase
import personaclick.demo_android.feature.productDetails.domain.usecase.GetRecommendationForProductUseCase
import personaclick.demo_android.feature.productDetails.presentation.viewmodel.ProductDetailsViewModel

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
        ProductMapper()
    }
    single {
        NavigationProductMapper()
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

package rees46.demo_android.feature.productDetails.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.productDetails.data.repository.ProductRepositoryImpl
import rees46.demo_android.feature.productDetails.domain.repository.ProductRepository
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
            product = product.get()
        )
    }
    single<ProductRepository> {
        ProductRepositoryImpl(
            sdk = get()
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

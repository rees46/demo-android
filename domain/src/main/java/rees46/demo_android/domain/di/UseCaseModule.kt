package rees46.demo_android.domain.di

import org.koin.dsl.module
import rees46.demo_android.domain.usecase.cart.AddProductToCartUseCase
import rees46.demo_android.domain.usecase.cart.GetCartProductUseCase
import rees46.demo_android.domain.usecase.cart.GetCartProductsUseCase
import rees46.demo_android.domain.usecase.cart.GetCartSumPriceUseCase
import rees46.demo_android.domain.usecase.cart.RemoveProductFromCartUseCase
import rees46.demo_android.domain.usecase.recommendation.GetRecommendationForProductUseCase
import rees46.demo_android.domain.usecase.recommendation.GetRecommendationUseCase
import rees46.demo_android.domain.usecase.search.SearchProductsUseCase
import rees46.demo_android.domain.usecase.search.SearchRecommendedProductsUseCase

object UseCaseModule {

    val cartModule = module {
        single {
            GetCartProductsUseCase(
                cartRepository = get()
            )
        }
        single {
            GetCartProductUseCase(
                cartRepository = get()
            )
        }
        single {
            AddProductToCartUseCase(
                cartRepository = get()
            )
        }
        single {
            RemoveProductFromCartUseCase(
                cartRepository = get()
            )
        }
        single {
            GetCartSumPriceUseCase(
                cartRepository = get()
            )
        }
    }

    val recommendationModule = module {
        single {
            GetRecommendationUseCase(
                recommendationRepository = get()
            )
        }
        single {
            GetRecommendationForProductUseCase(
                recommendationRepository = get()
            )
        }
    }

    val productModule = module {
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
}

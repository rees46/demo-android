package rees46.demo_android.data.di

import org.koin.dsl.module
import rees46.demo_android.data.repository.cart.CartRepositoryImpl
import rees46.demo_android.data.repository.cart.Cart
import rees46.demo_android.data.repository.product.ProductRepositoryImpl
import rees46.demo_android.data.repository.recommendation.RecommendationRepositoryImpl
import rees46.demo_android.domain.repository.CartRepository
import rees46.demo_android.domain.repository.ProductRepository
import rees46.demo_android.domain.repository.RecommendationRepository

object DataModule {

    val cartModule = module {
        single<CartRepository> {
            CartRepositoryImpl(
                sdk = get(),
                cart = Cart()
            )
        }
    }

    val recommendationModule = module {
        single<RecommendationRepository> {
            RecommendationRepositoryImpl(
                sdk = get()
            )
        }
    }

    val productModule = module {
        single<ProductRepository> {
            ProductRepositoryImpl(
                sdk = get()
            )
        }
    }
}

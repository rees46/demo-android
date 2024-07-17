package rees46.demo_android.domain.di

import org.koin.dsl.module
import rees46.demo_android.domain.features.productDetails.di.cardProductUseCaseModule
import rees46.demo_android.domain.features.cart.di.cartUseCaseModule
import rees46.demo_android.domain.features.recommendation.di.recommendationUseCaseModule
import rees46.demo_android.domain.features.search.di.searchUseCaseModule

object FeatureModule {

    val featureUseCaseModule = module {
        includes(
            cardProductUseCaseModule,
            cartUseCaseModule,
            searchUseCaseModule,
            recommendationUseCaseModule
        )
    }
}

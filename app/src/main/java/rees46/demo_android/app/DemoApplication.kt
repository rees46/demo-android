package rees46.demo_android.app

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import rees46.demo_android.app.di.appModule
import rees46.demo_android.core.di.sdkModule
import rees46.demo_android.feature.cart.di.cartModule
import rees46.demo_android.feature.category.di.categoryModule
import rees46.demo_android.feature.home.di.homeModule
import rees46.demo_android.feature.productDetails.di.productDetailsModule
import rees46.demo_android.feature.products.di.productsModule
import rees46.demo_android.feature.recommendationBlock.di.recommendationBlockModule
import rees46.demo_android.feature.search.di.searchModule
import rees46.demo_android.feature.settings.di.settingsModule

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val m = module {
            single<Context> { this@DemoApplication }
        }

        startKoin {
            androidContext(this@DemoApplication)

            modules(
                listOf(
                    appModule,
                    sdkModule(this@DemoApplication),
                    homeModule,
                    recommendationBlockModule,
                    cartModule,
                    categoryModule,
                    productDetailsModule,
                    productsModule,
                    settingsModule,
                    searchModule
                )
            )
        }
    }
}

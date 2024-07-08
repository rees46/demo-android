package rees46.demo_android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import rees46.demo_android.domain.feature.card_product.di.cardProductViewModelModule
import rees46.demo_android.domain.feature.di.sdkModule
import rees46.demo_android.domain.feature.main.cart.di.cartViewModelModule
import rees46.demo_android.domain.feature.main.di.mainViewModelModule
import rees46.demo_android.domain.feature.main.home.di.homeViewModelModule

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DemoApplication)

            modules(
                listOf(
                    sdkModule(this@DemoApplication),
                    homeViewModelModule,
                    cardProductViewModelModule,
                    cartViewModelModule,
                    mainViewModelModule
                )
            )
        }
    }
}

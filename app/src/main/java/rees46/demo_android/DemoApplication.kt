package rees46.demo_android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import rees46.demo_android.domain.feature.card_product.di.Module.cardProductFragmentModule
import rees46.demo_android.domain.feature.main.cart.di.Module.cartFragmentModule
import rees46.demo_android.domain.feature.main.di.Module.mainFragmentModule
import rees46.demo_android.domain.feature.main.home.di.Module.homeFragmentModule
import rees46.demo_android.domain.feature.di.FeatureModule.sdkModule

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DemoApplication)

            modules(listOf(sdkModule(this@DemoApplication), homeFragmentModule, cardProductFragmentModule, cartFragmentModule, mainFragmentModule))
        }
    }
}

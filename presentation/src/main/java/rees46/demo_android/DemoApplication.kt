package rees46.demo_android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import rees46.demo_android.domain.feature.di.sdkModule
import rees46.demo_android.data.di.DataModule
import rees46.demo_android.presentation.di.ViewModelModule

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DemoApplication)

            modules(
                listOf(
                    sdkModule(this@DemoApplication),
                    DataModule.cartModule,
                    DataModule.recommendationModule,
                    DataModule.productModule,
                    ViewModelModule.module
                )
            )
        }
    }
}

package rees46.demo_android

import android.app.Application
import android.content.SharedPreferences
import com.personalizatio.SDK
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class DemoApplication : Application() {

    private val appModule: Module by lazy {
        module {
            single {
                SDK().apply {
                    initialize(
                        context = this@DemoApplication,
                        shopId = SHOP_ID,
                        apiUrl = SDK_API_URL,
                        preferencesKey = SDK_PREFERENCES_KEY,
                        tag = SDK_TAG,
                        stream = SDK_STREAM
                    )
                }
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DemoApplication)
            modules(listOf(appModule))
        }
    }

    companion object {

        private const val SHOP_ID = "357382bf66ac0ce2f1722677c59511"
        private const val SDK_API_URL = "https://api.rees46.ru/"
        private const val SDK_PREFERENCES_KEY = "demo android"
        private const val SDK_TAG = ""
        private const val SDK_STREAM = "android"
    }
}
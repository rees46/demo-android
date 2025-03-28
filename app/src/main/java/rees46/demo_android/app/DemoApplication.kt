package rees46.demo_android.app

import android.app.Application
import com.personalization.SDK
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import rees46.demo_android.app.di.navigatorModule
import rees46.demo_android.core.di.sdkModule
import rees46.demo_android.core.utils.SdkUtils
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

        startKoin {
            androidContext(this@DemoApplication)

            modules(
                listOf(
                    navigatorModule,
                    sdkModule,
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

        initializeSdk()
    }

    private fun initializeSdk() {
        val sdk = getKoin().get<SDK>()
        SdkUtils.initialize(
            sdk = sdk,
            context = this@DemoApplication,
            shopId = SHOP_ID
        )
    }

    companion object {
        private const val SHOP_ID = "42a4cd11ebab3b0454778d18d4f3d5"
    }
}

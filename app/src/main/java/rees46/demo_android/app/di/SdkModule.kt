package rees46.demo_android.app.di

import android.content.Context
import com.personalizatio.SDK
import org.koin.core.module.Module
import org.koin.dsl.module
import rees46.demo_android.app.utils.SdkUtils

fun sdkModule(context: Context): Module {
    return module {
        single {
            SDK().apply {
                SdkUtils.initialize(this, context)
            }
        }
    }
}



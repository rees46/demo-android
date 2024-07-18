package rees46.demo_android.core.di

import android.content.Context
import com.personalizatio.SDK
import org.koin.core.module.Module
import org.koin.dsl.module
import rees46.demo_android.core.utils.SdkUtils

fun sdkModule(context: Context): Module {
    return module {
        single {
            SDK().apply {
                SdkUtils.initialize(
                    sdk = this,
                    context = context
                )
            }
        }
    }
}



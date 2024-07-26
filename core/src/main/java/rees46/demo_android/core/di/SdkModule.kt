package rees46.demo_android.core.di

import com.personalizatio.SDK
import org.koin.dsl.module

var sdkModule = module {
    single {
        SDK()
    }
}



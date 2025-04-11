package personaclick.demo_android.core.di

import com.personalization.SDK
import org.koin.dsl.module

var sdkModule = module {
    single {
        SDK()
    }
}

package rees46.demo_android.app.di

import androidx.navigation.NavController
import org.koin.dsl.module
import rees46.demo_android.app.navigation.AppNavigator
import rees46.demo_android.feature.Navigator

val appModule = module {
    single<Navigator> { (navController: NavController) ->
        AppNavigator(
            navController
        )
    }
}
package rees46.demo_android.app.di

import androidx.navigation.NavController
import com.rees46.demo_android.navigation.Navigator
import org.koin.dsl.module
import rees46.demo_android.app.navigation.AppNavigator

val navigatorModule = module {
    single<Navigator> {
        (navController: NavController) ->
            AppNavigator(
                navController = navController
            )
    }
}

package rees46.demo_android.app.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import rees46.demo_android.app.R
import rees46.demo_android.app.navigation.AppNavigator
import rees46.demo_android.feature.navigation.Navigator

val appModule = module {
    single<Navigator> { (activity: NavController) ->
        AppNavigator(
            activity
        )
    }
}
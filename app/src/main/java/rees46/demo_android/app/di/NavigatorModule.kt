package personaclick.demo_android.app.di

import androidx.navigation.NavController
import com.personaclick.demo_android.navigation.Navigator
import org.koin.dsl.module
import personaclick.demo_android.app.navigation.AppNavigator

val navigatorModule = module {
    single<Navigator> {
        (navController: NavController) ->
            AppNavigator(
                navController = navController
            )
    }
}

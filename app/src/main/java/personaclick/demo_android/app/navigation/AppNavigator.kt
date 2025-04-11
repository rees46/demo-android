package personaclick.demo_android.app.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import com.personaclick.demo_android.navigation.Destination
import com.personaclick.demo_android.navigation.Navigator
import com.personaclick.demo_android.navigation.ProductDetails
import com.personaclick.demo_android.navigation.ProductsDetails
import personaclick.demo_android.app.R
import personaclick.demo_android.core.settings.NavigationSettings

class AppNavigator(private val navController: NavController) : Navigator {

    override fun navigate(destination: Destination) {
        when (destination) {
            is ProductDetails -> {
                navigate(
                    resId = R.id.productDetailsFragment,
                    args = bundleOf(
                        NavigationSettings.PRODUCT_ARGUMENT_FIELD to destination.navigationProduct
                    )
                )
            }

            is ProductsDetails -> {
                navigate(
                    resId = R.id.productsFragment,
                    args = bundleOf(
                        NavigationSettings.PRODUCTS_ARGUMENT_FIELD to destination.navigationProducts
                    )
                )
            }

            else -> Unit
        }
    }

    override fun navigate(id: Int) {
        navController.navigate(id)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun getCurrentDestinationId(): Int? {
        return navController.currentDestination?.id
    }

    override fun getPreviousDestinationId(): Int? {
        return navController.previousBackStackEntry?.destination?.id
    }

    override fun addOnDestinationChangedListener(listener: OnDestinationChangedListener) {
        navController.addOnDestinationChangedListener(listener = listener)
    }

    private fun navigate(@IdRes resId: Int, args: Bundle?) {
        navController.navigate(
            resId = resId,
            args = args
        )
    }
}

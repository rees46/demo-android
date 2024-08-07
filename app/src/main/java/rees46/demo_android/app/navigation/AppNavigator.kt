package rees46.demo_android.app.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.rees46.demo_android.navigation.Destination
import com.rees46.demo_android.navigation.Navigator
import com.rees46.demo_android.navigation.ProductDetails
import com.rees46.demo_android.navigation.ProductsDetails
import rees46.demo_android.app.R
import rees46.demo_android.core.settings.NavigationSettings

class AppNavigator(private val navController: NavController) : Navigator {

    override fun navigate(destination: Destination) {
        when(destination) {
            is ProductDetails -> {
                val bundle = bundleOf(NavigationSettings.PRODUCT_ARGUMENT_FIELD to destination.navigationProduct)
                navigate(
                    resId = R.id.productDetailsFragment,
                    args = bundle
                )
            }
            is ProductsDetails -> {
                val bundle = bundleOf(NavigationSettings.PRODUCTS_ARGUMENT_FIELD to destination.navigationProducts)
                navigate(
                    resId = R.id.productsFragment,
                    args = bundle
                )
            }
            else -> {}
        }
    }

    override fun navigate(id: Int) {
        navController.navigate(id)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun getCurrentDestination() : Int? =
        navController.currentDestination?.id

    private fun navigate(@IdRes resId: Int, args: Bundle?) {
        navController.navigate(
            resId = resId,
            args = args
        )
    }
}

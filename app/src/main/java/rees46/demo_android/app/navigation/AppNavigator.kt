package rees46.demo_android.app.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import rees46.demo_android.app.R
import rees46.demo_android.core.utils.NavigationUtils
import rees46.demo_android.feature.Destination
import rees46.demo_android.feature.Navigator
import rees46.demo_android.feature.ProductDetails
import rees46.demo_android.feature.ProductsDetails

class AppNavigator(private val navController: NavController) : Navigator {

    override fun navigate(destination: Destination) {
        when(destination) {
            is ProductDetails -> {
                val bundle = bundleOf(NavigationUtils.PRODUCT_ARGUMENT_FIELD to destination.product)
                navigate(
                    resId = R.id.productDetailsFragment,
                    args = bundle
                )
            }
            is ProductsDetails -> {
                val bundle = bundleOf(NavigationUtils.PRODUCTS_ARGUMENT_FIELD to destination.products)
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

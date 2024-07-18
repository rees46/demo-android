package rees46.demo_android.app.navigation

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import rees46.demo_android.app.R
import rees46.demo_android.feature.Destination
import rees46.demo_android.feature.Navigator
import rees46.demo_android.feature.ProductDetails
import rees46.demo_android.feature.ProductsDetails

class AppNavigator(private val navController: NavController) : Navigator {

    @SuppressLint("RestrictedApi")
    override fun navigate(destination: Destination) {
        when(destination) {
            is ProductDetails -> {
                val bundle = bundleOf("product" to destination.product)
                navController.navigate(R.id.productDetailsFragment, bundle)
            }
            is ProductsDetails -> {
                val bundle = bundleOf("products" to destination.products)
                navController.navigate(R.id.productsFragment, bundle)
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
}
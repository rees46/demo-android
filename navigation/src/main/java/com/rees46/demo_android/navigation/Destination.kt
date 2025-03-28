package com.personaclick.demo_android.navigation

import com.personaclick.demo_android.navigation.models.NavigationProduct

sealed interface Destination
class ProductDetails(val navigationProduct: NavigationProduct) : Destination
class ProductsDetails(val navigationProducts: Collection<NavigationProduct>) : Destination

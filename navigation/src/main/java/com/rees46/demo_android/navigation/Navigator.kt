package com.personaclick.demo_android.navigation

import androidx.navigation.NavController

interface Navigator {
    fun navigate(destination: Destination)

    fun navigate(id: Int)

    fun popBackStack()
    fun getCurrentDestinationId() : Int?
    fun getPreviousDestinationId() : Int?

    fun addOnDestinationChangedListener(listener: NavController.OnDestinationChangedListener)
}

package com.rees46.demo_android.navigation

interface Navigator {
    fun navigate(destination: Destination)

    fun navigate(id: Int)

    fun popBackStack()
    fun getCurrentDestination() : Int?
}

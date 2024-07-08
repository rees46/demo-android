package rees46.demo_android.feature.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import rees46.demo_android.R

internal class MainScreenPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainPageFragment(R.navigation.home_navigation)
            1 -> MainPageFragment(R.navigation.category_navigation)
            2 -> MainPageFragment(R.navigation.cart_navigation)
            3 -> MainPageFragment(R.navigation.settings_navigation)
            else -> MainPageFragment(R.navigation.home_navigation)
        }
    }
}

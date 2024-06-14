package rees46.demo_android.features.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import rees46.demo_android.features.main.cart.CartFragment
import rees46.demo_android.features.main.category.CategoryFragment
import rees46.demo_android.features.main.home.HomeFragment
import rees46.demo_android.features.main.settings.SettingsFragment

internal class MainScreenPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> CategoryFragment()
            2 -> CartFragment()
            3 -> SettingsFragment()
            else -> HomeFragment()
        }
    }
}
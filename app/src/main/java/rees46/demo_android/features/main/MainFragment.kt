package rees46.demo_android.features.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.View
import rees46.demo_android.R
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentMainBinding

class MainFragment
    : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = MainScreenPagerAdapter(this)
        binding.viewPager.run {
            isUserInputEnabled = false
            adapter = pagerAdapter
        }

        binding.topAppBar.setNavigationOnClickListener {
            binding.root.open()
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> binding.viewPager.currentItem = 0
                R.id.category -> binding.viewPager.currentItem = 1
                R.id.cart -> binding.viewPager.currentItem = 2
                R.id.settings -> binding.viewPager.currentItem = 3
            }
            true
        }
    }
}
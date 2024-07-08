package rees46.demo_android.feature.main

import android.os.Bundle
import android.view.View
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentMainPageBinding

class MainPageFragment(@NavigationRes val graphResId: Int)
    : BaseFragment<FragmentMainPageBinding>(FragmentMainPageBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = binding.navHostFragment.getFragment<NavHostFragment>()
        navHostFragment.findNavController().setGraph(graphResId)
    }
}

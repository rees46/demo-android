package rees46.demo_android.features.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.features.recommendationBlock.ModelBuilder

class HomeFragment
    : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shortCardProducts = ModelBuilder.getShortCardProducts()
        binding.newArrivalsRecommendationBlockView.updateCardProducts(shortCardProducts)
    }
}

package rees46.demo_android.features.main.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.core_ui.RecommendationBlockView
import rees46.demo_android.features.product.Product

class HomeFragment
    : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.topTrendsProductsFlow.collectLatest { products ->
                run {
                    binding.topTrendsRecommendationBlockView.updateProducts(products)
                }
            }
        }
        binding.topTrendsRecommendationBlockView.setClickListener(getRecommendationTopTrendsProductClickListener())
        binding.topTrendsRecommendationBlockView.setHeaderText("Top trends")
    }

    private fun getRecommendationTopTrendsProductClickListener(): RecommendationBlockView.ClickListener {
        return object : RecommendationBlockView.ClickListener {
            override fun onCardProductClick(product: Product) {
                val action = HomeFragmentDirections.actionHomeFragmentToCardProductFragment(product)
                findNavController().navigate(action)
            }
        }
    }
}

package rees46.demo_android.features.main.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.personalizatio.SDK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.core_ui.RecommendationBlockView

class HomeFragment
    : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModel()

    private val sdk: SDK by inject()

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
    }

    private fun getRecommendationTopTrendsProductClickListener(): RecommendationBlockView.ClickListener {
        return object : RecommendationBlockView.ClickListener {
            override fun onCardProductClick(productId: String) {
                val product = viewModel.getTopTrendsProduct(productId)
                if (product != null) {
                    val action = HomeFragmentDirections.actionHomeFragmentToCardProductFragment(product)
                    findNavController().navigate(action)
                }
            }
        }
    }
}

package rees46.demo_android.feature.home.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.personalizatio.SDK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.feature.BaseFragment
import rees46.demo_android.feature.product.domain.models.ProductDto
import rees46.demo_android.feature.recommendationBlock.presentation.view.RecommendationBlockView
import rees46.demo_android.feature.home.presentation.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModel()

    private val sdk: SDK by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initStories()

        initRecommendationBlockView(binding.newArrivalsRecommendationBlockView)
        initRecommendationBlockView(binding.topTrendsRecommendationBlockView)
        initRecommendationBlockView(binding.youLikeRecommendationBlockView)
    }

    private fun initStories() {
        sdk.initializeStoriesView(binding.storiesView)
        binding.storiesView.settings.icon_size = 80
        binding.storiesView.settings.label_font_size = 0
    }

    private fun initRecommendationBlockView(recommendationBlockView: RecommendationBlockView) {
        lifecycleScope.launch {
            viewModel.recommendationFlow.collectLatest(recommendationBlockView::update)
        }
        recommendationBlockView.onCardProductClick = ::navigateProductFragment
        recommendationBlockView.onShowAllClick = ::navigateProductsFragment
    }

    private fun navigateProductFragment(product: ProductDto) {
        findNavController().navigate(
            directions = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product)
        )
    }

    private fun navigateProductsFragment(products: List<ProductDto>) {
        findNavController().navigate(
            directions = HomeFragmentDirections.actionHomeFragmentToProductsFragment(products.toTypedArray())
        )
    }
}

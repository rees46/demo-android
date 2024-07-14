package rees46.demo_android.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.personalizatio.SDK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.ui.base.BaseFragment
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.domain.entities.ProductDto
import rees46.demo_android.presentation.ui.recommendationBlock.RecommendationBlockView

class HomeFragment
    : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: rees46.demo_android.domain.feature.main.home.presentation.HomeViewModel by viewModel()

    private val sdk: SDK by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initStories()

        initRecommendationBlockView(binding.newArrivalsRecommendationBlockView, "New arrivals")
        initRecommendationBlockView(binding.topTrendsRecommendationBlockView, "Top trends")
        initRecommendationBlockView(binding.youLikeRecommendationBlockView, "Also you like")
    }

    private fun initStories() {
        sdk.initializeStoriesView(binding.storiesView)
        binding.storiesView.settings.icon_size = 80
        binding.storiesView.settings.label_font_size = 0
    }

    private fun initRecommendationBlockView(recommendationBlockView: RecommendationBlockView, header: String) {

        lifecycleScope.launch {
            viewModel.recommendationProductsFlow.collectLatest { products ->
                run {
                    recommendationBlockView.updateProducts(products)
                }
            }
        }
        recommendationBlockView.onCardProductClick = ::navigateProductFragment
        recommendationBlockView.setHeaderText(header)
    }

    private fun navigateProductFragment(product: ProductDto) {
        val action = HomeFragmentDirections.actionHomeFragmentToCardProductFragment(product)
        findNavController().navigate(action)
    }
}

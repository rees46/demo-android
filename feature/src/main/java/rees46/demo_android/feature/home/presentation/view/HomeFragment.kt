package rees46.demo_android.feature.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.personalization.SDK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.feature.recommendationBlock.presentation.view.RecommendationBlockView
import rees46.demo_android.feature.home.presentation.viewmodel.HomeViewModel
import com.rees46.demo_android.navigation.Navigator
import com.rees46.demo_android.navigation.NewFeatures
import com.rees46.demo_android.navigation.ProductDetails
import com.rees46.demo_android.navigation.ProductsDetails
import rees46.demo_android.R
import rees46.demo_android.feature.productDetails.domain.mappers.NavigationProductMapper
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val productItemMapper: ProductItemMapper by inject<ProductItemMapper>()
    private val navigationProductMapper: NavigationProductMapper by inject<NavigationProductMapper>()

    private lateinit var binding: FragmentHomeBinding

    private val sdk: SDK by inject()

    private val navigator by lazy {
        get<Navigator> {
            parametersOf(findNavController())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        sdk.initializeStoriesView(binding.story)

        with(binding) {
            setupRecommendationBlockView(
                recommendationBlockView = newArrivalsRecommendationBlockView,
                title = R.string.arrivals_title
            )
            setupRecommendationBlockView(
                recommendationBlockView = topTrendsRecommendationBlockView,
                title = R.string.trends_title,
            )
            setupRecommendationBlockView(
                recommendationBlockView = youLikeRecommendationBlockView,
                title = R.string.recommender_title
            )

            newFeatures.setOnClickListener {
                navigateToNewFeaturesFragment()
            }
        }
    }

    private fun setupRecommendationBlockView(
        recommendationBlockView: RecommendationBlockView,
        title: Int
    ) {
        recommendationBlockView.apply {
            setup(
                productItemMapper = productItemMapper,
                titleId = title,
                onCardProductClick = ::navigateProductFragment,
                onShowAllClick = ::navigateProductsFragment,
            )
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.recommendationFlow.collectLatest(::update)
            }
        }
    }

    private fun navigateToNewFeaturesFragment() {
        navigator.navigate(NewFeatures)
    }

    private fun navigateProductFragment(product: Product) {
        val navigationProduct = navigationProductMapper.toNavigationProduct(product)
        navigator.navigate(ProductDetails(navigationProduct))
    }

    private fun navigateProductsFragment(products: List<Product>) {
        val navigationProducts = navigationProductMapper.toNavigationProducts(products)
        navigator.navigate(ProductsDetails(navigationProducts))
    }
}

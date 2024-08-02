package rees46.demo_android.feature.home.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.personalizatio.SDK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.feature.recommendationBlock.presentation.view.RecommendationBlockView
import rees46.demo_android.feature.home.presentation.viewmodel.HomeViewModel
import rees46.demo_android.feature.Navigator
import rees46.demo_android.feature.ProductDetails
import rees46.demo_android.feature.ProductsDetails
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val productItemMapper: ProductItemMapper by inject<ProductItemMapper>()

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

        with(binding.storiesView) {
            sdk.initializeStoriesView(this)
            settings.icon_size = 80
            settings.label_font_size = 0
        }

        with(binding) {
            setupRecommendationBlockView(newArrivalsRecommendationBlockView)
            setupRecommendationBlockView(topTrendsRecommendationBlockView)
            setupRecommendationBlockView(youLikeRecommendationBlockView)
        }
    }

    private fun setupRecommendationBlockView(recommendationBlockView: RecommendationBlockView) {
        recommendationBlockView.apply {
            setup(
                productItemMapper = productItemMapper,
                onCardProductClick = ::navigateProductFragment,
                onShowAllClick = ::navigateProductsFragment
            )
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.recommendationFlow.collectLatest(::update)
            }
        }
    }

    private fun navigateProductFragment(product: Product) {
        navigator.navigate(ProductDetails(product))
    }

    private fun navigateProductsFragment(products: List<Product>) {
        navigator.navigate(ProductsDetails(products))
    }
}

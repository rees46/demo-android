package rees46.demo_android.feature.productDetails.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rees46.demo_android.ui.extensions.updateImage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.core.settings.NavigationSettings
import rees46.demo_android.databinding.FragmentProductDetailsBinding
import rees46.demo_android.feature.Navigator
import rees46.demo_android.feature.ProductsDetails
import rees46.demo_android.feature.productDetails.presentation.ProductAction
import rees46.demo_android.feature.productDetails.presentation.viewmodel.ProductDetailsViewModel
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper

class ProductDetailsFragment : Fragment() {

    private val viewModel: ProductDetailsViewModel by viewModel {
        val product = arguments?.getParcelable<Product>(NavigationSettings.PRODUCT_ARGUMENT_FIELD)
        parametersOf(product)
    }

    private lateinit var binding: FragmentProductDetailsBinding

    private val productItemMapper: ProductItemMapper by inject<ProductItemMapper>()

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
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentProductFlow.collect(::updateCardProductView)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.countCartProductFlow.collect(::updateCount)
        }
    }

    private fun setupViews() {
        setupCardAction(viewModel::proceedCartAction)

        setupRecommendationBlockView()
    }

    private fun setupRecommendationBlockView() {
        binding.recommendationBlock.apply {
            setup(
                productItemMapper = productItemMapper,
                onCardProductClick = ::updateProduct,
                onShowAllClick = ::navigateProductsFragment
            )
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.recommendationFlow.collectLatest(::update)
            }
        }
    }

    private fun setupCardAction(onCardActionClick: (ProductAction) -> Unit) {
        binding.apply {
            addToCartButton.setOnClickListener { onCardActionClick.invoke(ProductAction.ADD) }
            countCard.setOnClickListener(
                onMinusClick = { onCardActionClick.invoke(ProductAction.DECREASE) },
                onPlusClick = { onCardActionClick.invoke(ProductAction.INCREASE) }
            )
        }
    }

    private fun updateProduct(product: Product?) {
        viewModel.updateProduct(product)
    }

    private fun updateCardProductView(product: Product?) {
        product?.let {
            viewModel.updateRecommendationBlock(product.id)

            binding.apply {
                productImage.updateImage(product.pictureUrl)

                binding.productDetailsView.setProduct(product)
            }
        }
    }

    private fun updateCount(count: Int) {
       binding.countCard.updateCount(count)
    }

    private fun navigateProductsFragment(products: List<Product>) {
        navigator.navigate(ProductsDetails(products))
    }
}

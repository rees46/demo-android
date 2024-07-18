package rees46.demo_android.feature.productDetails.presentation.view

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.core.utils.ImageUtils
import rees46.demo_android.databinding.FragmentProductDetailsBinding
import rees46.demo_android.feature.productDetails.presentation.ProductAction
import rees46.demo_android.feature.productDetails.presentation.viewmodel.ProductDetailsViewModel
import rees46.demo_android.feature.productDetails.domain.models.ProductDto

class ProductDetailsFragment : Fragment() {

    private val viewModel: ProductDetailsViewModel by viewModel {
        val product = arguments?.getParcelable<ProductDto>("product")
        parametersOf(product)
    }

    private lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModel()
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.currentProductFlow.collect(::updateCardProductView)
        }

        lifecycleScope.launch {
            viewModel.recommendationFlow.collect(binding.recommendationBlock::update)
        }

        lifecycleScope.launch {
            viewModel.countCartProductFlow.collect(::updateCount)
        }
    }

    private fun setupViews() {
        setupCardAction(viewModel::proceedCartAction)

        binding.apply {
            oldPriceText.paintFlags += Paint.STRIKE_THRU_TEXT_FLAG

            recommendationBlock.apply {
                onCardProductClick = ::updateProduct
                onShowAllClick = ::navigateProductsFragment
            }
        }
    }

    private fun setupCardAction(onCardActionClick: (ProductAction) -> Unit) {
        binding.apply {
            addToCartButton.setOnClickListener { onCardActionClick.invoke(ProductAction.ADD) }
            minusButton.setOnClickListener { onCardActionClick.invoke(ProductAction.DECREASE) }
            plusButton.setOnClickListener { onCardActionClick.invoke(ProductAction.INCREASE) }
        }
    }

    private fun updateProduct(product: ProductDto) {
        viewModel.updateProduct(product)
    }

    private fun updateCardProductView(product: ProductDto) {
        viewModel.updateRecommendationBlock(product.id)

        binding.apply {
            ImageUtils.updateImage(productImage, productImage, product.pictureUrl)

            productNameText.text = product.name
            producerNameText.text = product.producerName
            priceText.text = product.priceFormatted
            oldPriceText.text = product.priceFullFormatted
            descriptionText.text = product.description
            productRatingBar.rating = product.rating
        }
    }

    private fun updateCount(count: Int) {
        binding.countInCartText.text = count.toString()
    }

    private fun navigateProductsFragment(products: List<ProductDto>) {
//        findNavController().navigate(
//            directions = ProductDetailsFragmentDirections.actionCardProductFragmentToProductsFragment(products.toTypedArray())
//        )
    }
}

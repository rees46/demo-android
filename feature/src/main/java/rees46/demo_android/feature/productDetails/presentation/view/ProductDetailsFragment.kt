package rees46.demo_android.feature.productDetails.presentation.view

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.feature.BaseFragment
import rees46.demo_android.feature.utils.ImageUtils
import rees46.demo_android.databinding.FragmentProductDetailsBinding
import rees46.demo_android.feature.productDetails.presentation.ProductAction
import rees46.demo_android.feature.productDetails.presentation.viewmodel.ProductDetailsViewModel
import rees46.demo_android.feature.product.domain.models.ProductDto

class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>(FragmentProductDetailsBinding::inflate) {

    private val args by navArgs<ProductDetailsFragmentArgs>()

    private val viewModel: ProductDetailsViewModel by viewModel {
        parametersOf(args.product)
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
        findNavController().navigate(
            directions = ProductDetailsFragmentDirections.actionCardProductFragmentToProductsFragment(products.toTypedArray())
        )
    }
}

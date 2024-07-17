package rees46.demo_android.app.ui.cardProduct

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.app.utils.onBackPressedNavigation
import rees46.demo_android.app.base.BaseFragment
import rees46.demo_android.app.utils.ImageUtils
import rees46.demo_android.databinding.FragmentCardProductBinding
import rees46.demo_android.domain.models.ProductDto

class CardProductFragment : BaseFragment<FragmentCardProductBinding>(FragmentCardProductBinding::inflate) {

    private val args by navArgs<CardProductFragmentArgs>()

    private val viewModel: CardProductViewModel by viewModel {
        parametersOf(args.product)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModel()
        onBackPressedNavigation()
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

    private fun setupCardAction(onCardActionClick: (CardAction) -> Unit) {
        binding.apply {
            addToCartButton.setOnClickListener { onCardActionClick.invoke(CardAction.ADD) }
            minusButton.setOnClickListener { onCardActionClick.invoke(CardAction.DECREASE) }
            plusButton.setOnClickListener { onCardActionClick.invoke(CardAction.INCREASE) }
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
        }
    }

    private fun updateCount(count: Int) {
        binding.countInCartText.text = count.toString()
    }

    private fun navigateProductsFragment(products: List<ProductDto>) {
        val action = CardProductFragmentDirections.actionCardProductFragmentToProductsFragment(products.toTypedArray())
        findNavController().navigate(action)
    }
}

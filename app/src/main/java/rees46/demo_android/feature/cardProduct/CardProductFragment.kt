package rees46.demo_android.feature.cardProduct

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.core.utils.onBackPressedNavigation
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCardProductBinding
import rees46.demo_android.entities.products.ProductEntity

class CardProductFragment
    : BaseFragment<FragmentCardProductBinding>(FragmentCardProductBinding::inflate) {

    private val args by navArgs<CardProductFragmentArgs>()

    private val viewModel: CardProductViewModel by viewModel { parametersOf(args) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recommendationBlock.onCardProductClick = ::updateProduct
        binding.recommendationBlock.setHeaderText("You also may like")
    }

    override fun onResume() {
        super.onResume()
        setupListeners()
        setupViewModel()
        onBackPressedNavigation()
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            viewModel.currentProductFlow.collect(::updateCardProductView)
        }
        lifecycleScope.launch {
            viewModel.recommendedProductsFlow.collect(binding.recommendationBlock::updateProducts)
        }

        lifecycleScope.launch {
            viewModel.countCartProductFlow.collect(binding.cardProductView::updateCount)
        }
    }

    private fun updateProduct(product: ProductEntity) {
        viewModel.updateProduct(product)
    }

    private fun updateCardProductView(product: ProductEntity) {
        viewModel.updateRecommendationBlock(product.id)
        binding.cardProductView.updateProduct(product)
    }

    private fun setupListeners() {
        binding.cardProductView.setupCartController(viewModel::proceedCartAction)
    }
}

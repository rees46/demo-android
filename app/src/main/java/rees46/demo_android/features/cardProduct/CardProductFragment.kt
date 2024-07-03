package rees46.demo_android.features.cardProduct

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.core.utils.onBackPressedNavigation
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.features.recommendationBlock.RecommendationBlockView
import rees46.demo_android.databinding.FragmentCardProductBinding
import rees46.demo_android.features.product.Product

class CardProductFragment
    : BaseFragment<FragmentCardProductBinding>(FragmentCardProductBinding::inflate),
    CardProductView.ClickListener {

    private val args by navArgs<CardProductFragmentArgs>()

    private val viewModel: CardProductViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProduct(args.product)

        binding.cardProductView.setListener(this)

        lifecycleScope.launch {
            viewModel.recommendedProductsFlow.collectLatest { products ->
                run {
                    binding.recommendationBlock.updateProducts(products)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.count.collectLatest { value ->
                binding.cardProductView.updateCount(value)
            }
        }

        binding.recommendationBlock.setClickListener(getRecommendationTopTrendsProductClickListener())
        binding.recommendationBlock.setHeaderText("You also may like")
    }

    private fun updateProduct(product: Product) {
        viewModel.updateProduct(product)

        updateCardProductView(product)
    }

    private fun updateCardProductView(product: Product) {
        viewModel.updateRecommendationBlock(product.id)

        binding.cardProductView.updateProduct(product)
    }

    private fun getRecommendationTopTrendsProductClickListener(): RecommendationBlockView.ClickListener {
        return object : RecommendationBlockView.ClickListener {
            override fun onCardProductClick(product: Product) {
                updateProduct(product)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        onBackPressedNavigation()
    }

    override fun onAddToCart() {
        viewModel.addToCart()
    }

    override fun decreaseCount() {
        viewModel.decreaseCount()
    }

    override fun increaseCount() {
        viewModel.increaseCount()
    }
}

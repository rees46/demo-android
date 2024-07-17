package rees46.demo_android.feature.cart.presentation.view

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.feature.BaseFragment
import rees46.demo_android.databinding.FragmentCartBinding
import rees46.demo_android.feature.product.domain.models.CartProductDto
import rees46.demo_android.feature.product.domain.models.ProductDto
import rees46.demo_android.feature.cart.presentation.adapter.CartProductsAdapter
import rees46.demo_android.feature.cart.presentation.viewmodel.CartViewModel

class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    private val viewModel: CartViewModel by viewModel()

    private val cartProductsAdapter = CartProductsAdapter(::removeProduct)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModels()
        initRecommendationBlockView()
    }

    private fun setupViews() {
        binding.cartProductsRecyclerView.adapter = cartProductsAdapter
    }

    private fun setupViewModels() {
        lifecycleScope.launch {
            viewModel.cartProductsFlow.collectLatest(::updateCart)
        }
        lifecycleScope.launch {
            viewModel.sumPriceFlow.collectLatest {
                binding.totalValueText.text = "$it"
            }
        }
        viewModel.updateCarts()
    }

    private fun updateCart(newList: MutableList<CartProductDto>) {
        updateCartView(newList.isEmpty())

        lifecycleScope.launch {
            cartProductsAdapter.submitList(newList)
        }
    }

    private fun updateCartView(isEmpty: Boolean) {
        binding.apply {
            cartLayout.isVisible = !isEmpty
            headerText.isVisible = !isEmpty
            emptyCartText.isVisible = isEmpty
            recyclerContainer.isVisible = !isEmpty
        }
    }

    private fun removeProduct(cartProduct: CartProductDto) {
        viewModel.removeProduct(cartProduct)
    }

    private fun initRecommendationBlockView() {
        lifecycleScope.launch {
            viewModel.recommendationFlow.collect(binding.recommendationBlock::update)
        }
        binding.recommendationBlock.apply {
            onCardProductClick = ::navigateProductFragment
            onShowAllClick = ::navigateProductsFragment
        }
    }

    private fun navigateProductFragment(product: ProductDto) {
        findNavController().navigate(
            directions = CartFragmentDirections.actionCartFragmentToProductDetailsFragment(product)
        )
    }

    private fun navigateProductsFragment(products: List<ProductDto>) {
        findNavController().navigate(
            directions = CartFragmentDirections.actionCartFragmentToProductsFragment(products.toTypedArray())
        )
    }
}
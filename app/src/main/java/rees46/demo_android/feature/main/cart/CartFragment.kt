package rees46.demo_android.feature.main.cart

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCartBinding
import rees46.demo_android.entities.products.CartProductEntity
import rees46.demo_android.feature.main.cart.presentation.CartViewModel

class CartFragment
    : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    private val viewModel: CartViewModel by viewModel()

    private val cartProductsAdapter = CartProductsAdapter(::removeProduct)

    override fun onResume() {
        super.onResume()
        binding.cartProductsRecyclerView.adapter = cartProductsAdapter
        lifecycleScope.launch {
            viewModel.cartProductsFlow.collectLatest(::updateCart)
        }
        lifecycleScope.launch {
            viewModel.pricesFlow.collectLatest {
                binding.totalValueText.text = "$it"
            }
        }
        viewModel.updateCarts()

        initRecommendationBlockView()
    }

    private fun updateCart(newList: MutableList<CartProductEntity>) {
        if(newList.isEmpty()) {
            binding.cartLayout.isVisible = false
            binding.headerText.isVisible = false
            binding.emptyCartText.isVisible = true
            binding.recyclerContainer.isVisible = false
        }
        else {
            binding.headerText.isVisible = true
            binding.cartLayout.isVisible = true
            binding.emptyCartText.isVisible = false
            binding.recyclerContainer.isVisible = true
        }

        lifecycleScope.launch {
            cartProductsAdapter.submitList(newList)
        }
    }

    private fun removeProduct(cartProduct: CartProductEntity) {
        viewModel.removeProduct(cartProduct)
    }

    private fun initRecommendationBlockView() {
        lifecycleScope.launch {
            viewModel.recommendationProductsFlow.collect(binding.recommendationBlock::updateProducts)
        }
        binding.recommendationBlock.onCardProductClick = ::navigateProductFragment
        binding.recommendationBlock.setHeaderText("Also bought")
    }

    private fun navigateProductFragment(product: rees46.demo_android.entities.products.ProductEntity) {
        val action = CartFragmentDirections.actionCartFragmentToCardProductFragment(product)
        findNavController().navigate(action)
    }
}

package rees46.demo_android.app.ui.cart

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.app.ui.base.BaseFragment
import rees46.demo_android.databinding.FragmentCartBinding
import rees46.demo_android.domain.entities.CartProductEntity
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.app.ui.cart.adapter.CartProductsAdapter

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
            viewModel.sumPriceFlow.collectLatest {
                binding.totalValueText.text = "$it"
            }
        }
        viewModel.updateCarts()

        initRecommendationBlockView()
    }

    private fun updateCart(newList: MutableList<CartProductEntity>) {
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

    private fun removeProduct(cartProduct: CartProductEntity) {
        viewModel.removeProduct(cartProduct)
    }

    private fun initRecommendationBlockView() {
        lifecycleScope.launch {
            viewModel.recommendationFlow.collect(binding.recommendationBlock::update)
        }
        binding.recommendationBlock.onCardProductClick = ::navigateProductFragment
    }

    private fun navigateProductFragment(product: ProductEntity) {
        val action = CartFragmentDirections.actionCartFragmentToCardProductFragment(product)
        findNavController().navigate(action)
    }
}

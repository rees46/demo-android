package rees46.demo_android.features.main.cart

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCartBinding
import rees46.demo_android.entity.productsEntity.CartProductEntity

class CartFragment
    : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    private val viewModel: CartViewModel by viewModel()

    private val shortCardProductsAdapter = CartProductsAdapter(::removeProduct)

    override fun onResume() {
        super.onResume()
        binding.cartProductsRecyclerView.adapter = shortCardProductsAdapter
        lifecycleScope.launch {
            viewModel.cartProductsFlow.collectLatest(::updateCartAdapter)
        }
        viewModel.updateCarts()
    }

    private fun updateCartAdapter(newList: MutableList<CartProductEntity>) = lifecycleScope.launch {
        shortCardProductsAdapter.submitList(newList)
    }

    private fun removeProduct(cartProduct: CartProductEntity) {
        viewModel.removeProduct(cartProduct)
    }
}

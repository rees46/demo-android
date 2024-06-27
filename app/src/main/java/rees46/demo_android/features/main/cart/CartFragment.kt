package rees46.demo_android.features.main.cart

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCartBinding

class CartFragment
    : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate), CartProductView.ClickListener {

    private val viewModel: CartViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shortCardProductsAdapter = CartProductsAdapter(requireContext(), this)
        binding.cartProductsRecyclerView.adapter = shortCardProductsAdapter

        lifecycleScope.launch {
            viewModel.cartProductsFlow.collectLatest { cartProducts ->
                run {
                    shortCardProductsAdapter.updateCartProducts(cartProducts)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.updateCart()
    }

    override fun removeProduct(cartProduct: CartProduct) {
        viewModel.removeProduct(cartProduct)
    }
}

package rees46.demo_android.features.main.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCartBinding

class CartFragment
    : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate), CartProductsAdapter.ClickListener {

    private val viewModel: CartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val shortCardProductsAdapter = CartProductsAdapter(requireContext(), viewModel.products, this)
        //binding.cartProductsRecyclerView.adapter = shortCardProductsAdapter
    }

    override fun onCartProductClick(productId: String) {
    }
}

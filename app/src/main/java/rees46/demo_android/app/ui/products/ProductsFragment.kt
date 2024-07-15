package rees46.demo_android.app.ui.products

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.app.base.BaseFragment
import rees46.demo_android.app.ui.products.adapter.CardProductsAdapter
import rees46.demo_android.app.utils.onBackPressedNavigation
import rees46.demo_android.databinding.FragmentProductsBinding
import rees46.demo_android.domain.entities.ProductEntity

class ProductsFragment : BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate),
    CardProductsAdapter.ClickListener {

    private val args by navArgs<ProductsFragmentArgs>()

    private val viewModel: ProductsViewModel by viewModel()

    private val gridLayoutCount = 2

    override fun onResume() {
        super.onResume()

        binding.cardProductsRecyclerView.adapter = CardProductsAdapter(requireContext(), args.products.toList(), this)
        binding.cardProductsRecyclerView.layoutManager = GridLayoutManager(context, gridLayoutCount)

        onBackPressedNavigation()
    }

    override fun onCardProductClick(product: ProductEntity) {
        navigateProductFragment(product)
    }

    private fun navigateProductFragment(product: ProductEntity) {
        val action = ProductsFragmentDirections.actionProductsFragmentToCardProductFragment(product)
        findNavController().navigate(action)
    }
}

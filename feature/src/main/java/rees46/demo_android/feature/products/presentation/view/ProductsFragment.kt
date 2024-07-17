package rees46.demo_android.feature.products.presentation.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.feature.BaseFragment
import rees46.demo_android.feature.products.presentation.adapter.CardProductsAdapter
import rees46.demo_android.databinding.FragmentProductsBinding
import rees46.demo_android.feature.products.presentation.viewmodel.ProductsViewModel
import rees46.demo_android.feature.product.domain.models.ProductDto

class ProductsFragment : BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate),
    CardProductsAdapter.ClickListener {

    private val args by navArgs<ProductsFragmentArgs>()

    private val viewModel: ProductsViewModel by viewModel()

    private val gridLayoutCount = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.cardProductsRecyclerView.adapter = CardProductsAdapter(requireContext(), args.products.toList(), this)
        binding.cardProductsRecyclerView.layoutManager = GridLayoutManager(context, gridLayoutCount)
    }

    override fun onCardProductClick(product: ProductDto) {
        navigateProductFragment(product)
    }

    private fun navigateProductFragment(product: ProductDto) {
        findNavController().navigate(
            directions = ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(product)
        )
    }
}

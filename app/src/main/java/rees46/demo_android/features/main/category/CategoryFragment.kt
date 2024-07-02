package rees46.demo_android.features.main.category

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCategoryBinding

class CategoryFragment
    : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate), CardProductsAdapter.ClickListener {

    private val viewModel: CategoryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardProductsAdapter = CardProductsAdapter(requireContext(), this)
        binding.cardProductsRecyclerView.adapter = cardProductsAdapter
        binding.cardProductsRecyclerView.layoutManager = GridLayoutManager(context, 2)

        lifecycleScope.launch {
            viewModel.productsFlow.collectLatest { products ->
                run {
                    cardProductsAdapter.updateProducts(products)
                }
            }
        }
    }

    override fun onCardProductClick(productId: String) {
    }
}

package rees46.demo_android.feature.cart.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.databinding.FragmentCartBinding
import rees46.demo_android.feature.product.domain.models.CartProductDto
import rees46.demo_android.feature.product.domain.models.ProductDto
import rees46.demo_android.feature.cart.presentation.adapter.CartProductsAdapter
import rees46.demo_android.feature.cart.presentation.viewmodel.CartViewModel
import rees46.demo_android.feature.navigation.Navigator
import rees46.demo_android.feature.navigation.ProductDetails
import rees46.demo_android.feature.navigation.ProductsDetails

class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModel()

    private lateinit var binding: FragmentCartBinding

    private lateinit var cartProductsAdapter: CartProductsAdapter

    private val navigator by lazy {
        get<Navigator> {
            parametersOf(findNavController())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModels()
        initRecommendationBlockView()
    }

    private fun setupViews() {
        cartProductsAdapter = CartProductsAdapter(requireContext(), ::removeProduct)
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
        navigator.navigate(ProductDetails(product))
    }

    private fun navigateProductsFragment(products: List<ProductDto>) {
        navigator.navigate(ProductsDetails(products))
    }
}

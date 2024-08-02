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
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.databinding.FragmentCartBinding
import rees46.demo_android.feature.cart.presentation.adapter.CartProductsAdapter
import rees46.demo_android.feature.cart.presentation.viewmodel.CartViewModel
import rees46.demo_android.feature.Navigator
import rees46.demo_android.feature.ProductDetails
import rees46.demo_android.feature.ProductsDetails
import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper

class CartFragment : Fragment() {

    private val viewModel: CartViewModel by viewModel()
    private val productItemMapper: ProductItemMapper by inject<ProductItemMapper>()

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

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupViewModels()
    }

    private fun setupViews() {
        cartProductsAdapter = CartProductsAdapter(
            context = requireContext(),
            onClickRemoveCart = ::removeProduct
        )
        binding.cartProductsRecyclerView.adapter = cartProductsAdapter

        setupRecommendationBlockView()
    }

    private fun setupViewModels() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cartProductsFlow.collectLatest(::updateCart)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sumPriceFlow.collectLatest {
                binding.totalValueText.text = "$it"
            }
        }
    }

    private fun updateCart(newList: MutableList<CartProduct>) {
        updateCartView(newList.isEmpty())

        viewLifecycleOwner.lifecycleScope.launch {
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

    private fun removeProduct(cartProduct: CartProduct) {
        viewModel.removeProduct(cartProduct)
    }

    private fun setupRecommendationBlockView() {
        binding.recommendationBlock.apply {
            setup(
                productItemMapper = productItemMapper,
                onCardProductClick = ::navigateProductFragment,
                onShowAllClick = ::navigateProductsFragment
            )
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.recommendationFlow.collect(::update)
            }
        }
    }

    private fun navigateProductFragment(product: Product) {
        navigator.navigate(ProductDetails(product))
    }

    private fun navigateProductsFragment(products: List<Product>) {
        navigator.navigate(ProductsDetails(products))
    }
}

package personaclick.demo_android.feature.cart.presentation.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import personaclick.demo_android.feature.cart.presentation.models.CartProductRecyclerViewItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import personaclick.demo_android.databinding.FragmentCartBinding
import personaclick.demo_android.feature.cart.presentation.viewmodel.CartViewModel
import com.personaclick.demo_android.navigation.Navigator
import com.personaclick.demo_android.navigation.ProductDetails
import com.personaclick.demo_android.navigation.ProductsDetails
import personaclick.demo_android.feature.cart.domain.models.CartProduct
import personaclick.demo_android.feature.cart.presentation.mappers.CartProductItemMapper
import personaclick.demo_android.feature.productDetails.domain.mappers.NavigationProductMapper
import personaclick.demo_android.feature.productDetails.domain.models.Product
import personaclick.demo_android.feature.products.presentation.mappers.ProductItemMapper

class CartFragment : Fragment(), OnItemClickListener {

    private val viewModel: CartViewModel by viewModel()
    private val productItemMapper: ProductItemMapper by inject<ProductItemMapper>()
    private val cartProductItemMapper: CartProductItemMapper by inject<CartProductItemMapper>()
    private val navigationProductMapper: NavigationProductMapper by inject<NavigationProductMapper>()

    private lateinit var binding: FragmentCartBinding

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
        binding.cartProductsRecyclerView.setup(this)

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

    private fun updateCart(cartProducts: MutableList<CartProduct>) {
        updateCartView(cartProducts.isEmpty())

        viewLifecycleOwner.lifecycleScope.launch {

        }
        val cartProductItems = cartProductItemMapper.toCartProductItems(cartProducts)
        Handler(requireContext().mainLooper).post {
            binding.cartProductsRecyclerView.updateItems(cartProductItems)
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
        val navigationProduct = navigationProductMapper.toNavigationProduct(product)
        navigator.navigate(ProductDetails(navigationProduct))
    }

    private fun navigateProductsFragment(products: List<Product>) {
        val navigationProducts = navigationProductMapper.toNavigationProducts(products)
        navigator.navigate(ProductsDetails(navigationProducts))
    }

    override fun onItemClick(item: RecyclerViewItem) {
        val cartProduct = cartProductItemMapper.toCartProduct(item as CartProductRecyclerViewItem)
        removeProduct(cartProduct)
    }
}

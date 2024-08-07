package rees46.demo_android.feature.products.presentation.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.databinding.FragmentProductsBinding
import rees46.demo_android.feature.Navigator
import rees46.demo_android.feature.ProductDetails
import rees46.demo_android.feature.products.presentation.viewmodel.ProductsViewModel
import rees46.demo_android.feature.productDetails.domain.models.Product
import com.rees46.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import rees46.demo_android.core.settings.NavigationSettings
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper

class ProductsFragment : Fragment(), OnItemClickListener {

    private val viewModel: ProductsViewModel by viewModel()
    private val productItemMapper: ProductItemMapper by inject<ProductItemMapper>()

    private lateinit var binding: FragmentProductsBinding

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
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        binding.productsRecyclerView.apply {
            setup(this@ProductsFragment)
            val products = arguments?.getParcelableArrayList<Product>(NavigationSettings.PRODUCTS_ARGUMENT_FIELD)
            products?.let {
                Handler(requireContext().mainLooper).post {
                    val productItems = productItemMapper.toProductItems(products)
                    updateItems(productItems)
                }
            }
        }
    }

    private fun navigateProductFragment(product: Product) {
        navigator.navigate(ProductDetails(product))
    }

    override fun onItemClick(item: RecyclerViewItem) {
        val product = productItemMapper.toProduct(item as ProductRecyclerViewItem)
        navigateProductFragment(product)
    }
}

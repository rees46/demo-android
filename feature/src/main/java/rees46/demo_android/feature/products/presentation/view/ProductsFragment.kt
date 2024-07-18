package rees46.demo_android.feature.products.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.core.utils.ViewUtils
import rees46.demo_android.feature.products.presentation.adapter.ProductsAdapter
import rees46.demo_android.databinding.FragmentProductsBinding
import rees46.demo_android.feature.Navigator
import rees46.demo_android.feature.ProductDetails
import rees46.demo_android.feature.products.presentation.viewmodel.ProductsViewModel
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.products.presentation.adapter.ProductViewSettings

class ProductsFragment : Fragment(), ProductsAdapter.OnClickListener {

    private val viewModel: ProductsViewModel by viewModel()

    private lateinit var binding: FragmentProductsBinding

    private val gridLayoutCount = 2

    private val navigator by lazy {
        get<Navigator> {
            parametersOf(findNavController())
        }
    }

    private lateinit var productViewSettings: ProductViewSettings

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupProductViewSettings()
        setupViews()
    }

    private fun setupViews() {
        binding.productsRecyclerView.apply {
            val products = arguments?.getParcelableArrayList<Product>("products")
            adapter = products?.let {
                ProductsAdapter(
                    context = requireContext(),
                    products = it,
                    productViewSettings = productViewSettings,
                    listener = this@ProductsFragment
                )
            }
            layoutManager = GridLayoutManager(context, gridLayoutCount)
        }
    }

    override fun onCardProductClick(product: Product) {
        navigateProductFragment(product)
    }

    private fun navigateProductFragment(product: Product) {
        navigator.navigate(ProductDetails(product))
    }

    private fun setupProductViewSettings() {
        productViewSettings = ProductViewSettings(
            width = ViewUtils.convertDpToPixel(171f, requireContext()).toInt(),
            showButton = true
        )
    }
}

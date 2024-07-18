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
import rees46.demo_android.feature.products.presentation.adapter.CardProductsAdapter
import rees46.demo_android.databinding.FragmentProductsBinding
import rees46.demo_android.feature.navigation.Navigator
import rees46.demo_android.feature.navigation.ProductDetails
import rees46.demo_android.feature.products.presentation.viewmodel.ProductsViewModel
import rees46.demo_android.feature.product.domain.models.ProductDto

class ProductsFragment : Fragment(), CardProductsAdapter.ClickListener {

    private val viewModel: ProductsViewModel by viewModel()

    private lateinit var binding: FragmentProductsBinding

    private val gridLayoutCount = 2

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.cardProductsRecyclerView.apply {
//            val products = arguments?.getParcelable<Collection<ProductDto>>("products")
//            products?.let { products ->
//                adapter = CardProductsAdapter(requireContext(), products, this@ProductsFragment)
//            }
            layoutManager = GridLayoutManager(context, gridLayoutCount)
        }
    }

    override fun onCardProductClick(product: ProductDto) {
        navigateProductFragment(product)
    }

    private fun navigateProductFragment(product: ProductDto) {
        navigator.navigate(ProductDetails(product))
    }
}

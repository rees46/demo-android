package rees46.demo_android.feature.search.presentation.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rees46.demo_android.R
import rees46.demo_android.core.utils.backPressedInvoke
import rees46.demo_android.databinding.FragmentSearchBinding
import rees46.demo_android.feature.Navigator
import rees46.demo_android.feature.ProductDetails
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper
import rees46.demo_android.feature.search.presentation.adapter.SearchResultCategoriesAdapter
import rees46.demo_android.feature.search.presentation.viewmodel.SearchViewModel

class SearchFragment : Fragment(), OnItemClickListener {

    private val viewModel: SearchViewModel by viewModel()
    private val productItemMapper: ProductItemMapper by inject<ProductItemMapper>()

    private lateinit var binding: FragmentSearchBinding

    private val navigator by lazy {
        get<Navigator> {
            parametersOf(findNavController())
        }
    }

    private val searchResultCategoriesAdapter = SearchResultCategoriesAdapter { _ -> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        binding.cancelButton.setOnClickListener {
            backPressedInvoke()
        }

        setupSearch()
    }

    private fun setupSearch() {
        binding.textInput.addTextChangedListener {
            viewModel.searchProduct(query = it?.toString() ?: "")
        }

        setupSearchResultProductsView()
        setupSearchResultCategoriesView()
    }

    private fun setupSearchResultProductsView() {
        binding.searchResultRecyclerView.setup(this)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResultItems.collectLatest {
                val resString = if(it.isEmpty()) R.string.suitable_products_not_found else R.string.suitable_products
                binding.suitableProductsText.text = getString(resString)

                val productItems = productItemMapper.toProductItems(it)
                Handler(requireContext().mainLooper).post {
                    binding.searchResultRecyclerView.updateItems(productItems)
                }
            }
        }
    }

    private fun setupSearchResultCategoriesView() {
        binding.searchResultCategoriesRecyclerView.adapter = searchResultCategoriesAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResultCategoriesItems.collect {
                binding.suitableCategoriesText.isVisible = it.isEmpty()

                searchResultCategoriesAdapter.submitList(it)
            }
        }
    }

    override fun onItemClick(item: Item) {
        val product = productItemMapper.toProduct(item as ProductItem)

        navigator.navigate(ProductDetails(product))
    }
}

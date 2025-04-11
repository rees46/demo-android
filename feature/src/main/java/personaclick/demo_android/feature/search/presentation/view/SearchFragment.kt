package personaclick.demo_android.feature.search.presentation.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.personaclick.demo_android.ui.extensions.backPressedInvoke
import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import personaclick.demo_android.R
import personaclick.demo_android.databinding.FragmentSearchBinding
import com.personaclick.demo_android.navigation.Navigator
import com.personaclick.demo_android.navigation.ProductDetails
import personaclick.demo_android.feature.productDetails.domain.mappers.NavigationProductMapper
import personaclick.demo_android.feature.search.presentation.mappers.SearchItemMapper
import personaclick.demo_android.feature.search.presentation.viewmodel.SearchViewModel

class SearchFragment : Fragment(), OnItemClickListener {

    private val viewModel: SearchViewModel by viewModel()

    private val searchItemMapper: SearchItemMapper by inject<SearchItemMapper>()
    private val navigationProductMapper: NavigationProductMapper by inject<NavigationProductMapper>()

    private lateinit var binding: FragmentSearchBinding

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

        setupSearchResultView()
    }

    private fun setupSearchResultView() {
        binding.searchResultRecyclerView.setup(this)
        binding.searchResultCategoriesRecyclerView.setup(this)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchResultItems.collectLatest {
                val resString =
                    if (it.products.isEmpty()) R.string.suitable_products_not_found else R.string.suitable_products
                binding.suitableProductsText.text = getString(resString)

                binding.suitableCategoriesText.isVisible = it.categories.isEmpty()

                Handler(requireContext().mainLooper).post {
                    val searchItem = searchItemMapper.toSearchItem(it)
                    binding.searchResultRecyclerView.updateItems(searchItem.productItems)
                    binding.searchResultCategoriesRecyclerView.updateItems(searchItem.categoryItems)
                }
            }
        }
    }

    override fun onItemClick(item: RecyclerViewItem) {
        if (item is ProductRecyclerViewItem) {
            val navigationProduct = navigationProductMapper.toNavigationProduct(item)
            navigator.navigate(ProductDetails(navigationProduct))
        } else {
            // Handle other item types if necessary
        }
    }
}

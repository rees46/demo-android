package rees46.demo_android.app.ui.main

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.search.SearchView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.R
import rees46.demo_android.app.base.BaseFragment
import rees46.demo_android.databinding.FragmentMainBinding
import rees46.demo_android.app.utils.fragmentOffFullScreen
import rees46.demo_android.app.utils.fragmentOnFullScreen
import rees46.demo_android.app.utils.showSystemBar
import rees46.demo_android.app.ui.main.adapter.MainScreenPagerAdapter
import rees46.demo_android.app.ui.main.adapter.MainScreenSearchResultAdapter
import rees46.demo_android.app.ui.main.adapter.MainScreenSearchResultCategoriesAdapter
import rees46.demo_android.domain.features.main.presentation.MainViewModel

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModel()

    private val searchResultAdapter = MainScreenSearchResultAdapter { product ->
        showSystemBar()
        val action = MainFragmentDirections.actionHomeFragmentToCardProductFragment(product)
        findNavController().navigate(action)
    }
    private val searchResultCategoriesAdapter = MainScreenSearchResultCategoriesAdapter { _ -> }
    private val pagerAdapter by lazy {
        MainScreenPagerAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        setupBottomNavigationView()
        setupSearchResultView()
        createOptionMenu()
        setupTopAppBar()
        setupSearchView()
    }

    private fun setupTopAppBar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.topAppBar)
        binding.topAppBar.setNavigationOnClickListener {
            binding.root.open()
        }
    }

    private fun setupSearchResultView() {
        setupSearchResultProductsView()
        setupSearchResultCategoriesView()
    }

    private fun setupBottomNavigationView() {
        binding.viewPager.run {
            isUserInputEnabled = false
            adapter = pagerAdapter
        }
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> binding.viewPager.currentItem = 0
                R.id.category -> binding.viewPager.currentItem = 1
                R.id.cart -> binding.viewPager.currentItem = 2
                R.id.settings -> binding.viewPager.currentItem = 3
            }
            true
        }
    }

    private fun setupSearchView() {
        binding.searchView.run {
            setupWithSearchBar(binding.topAppBar)
            addTransitionListener { _, _, newState ->
                when (newState) {
                    SearchView.TransitionState.SHOWING -> fragmentOnFullScreen(binding.bottomNavigation)
                    SearchView.TransitionState.HIDING -> fragmentOffFullScreen(binding.bottomNavigation)
                    else -> {}
                }
            }
            editText.addTextChangedListener {
                viewModel.searchProduct(query = it?.toString() ?: "")
            }
        }
    }

    private fun createOptionMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_top_app_bar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.menu_top_app_cart) {
                    binding.bottomNavigation.selectedItemId = R.id.cart
                    return true
                }

                return false
            }
        })
    }

    private fun setupSearchResultProductsView() {
        binding.searchResultRecyclerView.adapter = searchResultAdapter
        lifecycleScope.launch {
            viewModel.searchResultItems.collect {
                binding.suitableProductsText.text =
                    getString(if(it.isEmpty()) R.string.suitable_products_not_found else R.string.suitable_products)

                searchResultAdapter.submitList(it)
            }
        }
    }

    private fun setupSearchResultCategoriesView() {
        binding.searchResultCategoriesRecyclerView.adapter = searchResultCategoriesAdapter
        lifecycleScope.launch {
            viewModel.searchResultCategoriesItems.collect {
                binding.suitableCategoriesText.visibility =
                    if(it.isEmpty()) View.GONE else View.VISIBLE

                searchResultCategoriesAdapter.submitList(it)
            }
        }
    }
}

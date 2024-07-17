package rees46.demo_android.app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.MenuProvider
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.R
import rees46.demo_android.app.ui.search.adapter.SearchResultAdapter
import rees46.demo_android.app.ui.search.adapter.SearchResultCategoriesAdapter
import rees46.demo_android.databinding.ActivityMainBinding
import rees46.demo_android.domain.features.search.presentation.SearchViewModel

class MainActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    private val searchResultAdapter = SearchResultAdapter { product ->
    }
    private val searchResultCategoriesAdapter = SearchResultCategoriesAdapter { _ -> }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        setupTopAppBar()
        setupBottomNavigationView()
        createOptionMenu()
        setupSearchResultView()
        setupSearchView()
    }

    private fun setupTopAppBar() {
        setSupportActionBar(binding.topAppBar)
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> navController.navigate(R.id.homeFragment)
                R.id.category -> navController.navigate(R.id.categoryFragment)
                R.id.cart -> navController.navigate(R.id.cartFragment)
                R.id.settings -> navController.navigate(R.id.settingsFragment)
            }

            true
        }
    }

    private fun createOptionMenu() {
        addMenuProvider(object : MenuProvider {
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

    private fun setupSearchResultView() {
        setupSearchResultProductsView()
        setupSearchResultCategoriesView()
    }

    private fun setupSearchView() {
        binding.searchView.run {
            setupWithSearchBar(binding.topAppBar)
            editText.addTextChangedListener {
                searchViewModel.searchProduct(query = it?.toString() ?: "")
            }
        }
    }

    private fun setupSearchResultProductsView() {
        binding.searchResultRecyclerView.adapter = searchResultAdapter
        lifecycleScope.launch {
            searchViewModel.searchResultItems.collect {
                binding.suitableProductsText.text =
                    getString(if(it.isEmpty()) R.string.suitable_products_not_found else R.string.suitable_products)

                searchResultAdapter.submitList(it)
            }
        }
    }

    private fun setupSearchResultCategoriesView() {
        binding.searchResultCategoriesRecyclerView.adapter = searchResultCategoriesAdapter
        lifecycleScope.launch {
            searchViewModel.searchResultCategoriesItems.collect {
                binding.suitableCategoriesText.visibility =
                    if(it.isEmpty()) View.GONE else View.VISIBLE

                searchResultCategoriesAdapter.submitList(it)
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}

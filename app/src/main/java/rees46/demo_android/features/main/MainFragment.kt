package rees46.demo_android.features.main

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentMainBinding
import rees46.demo_android.utils.fragmentOffFullScreen
import rees46.demo_android.utils.fragmentOnFullScreen
import rees46.demo_android.utils.showSystemBar

class MainFragment
    : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModel()

    private val adapter = MainScreenSearchResultAdapter { product ->
        showSystemBar()
        val action = MainFragmentDirections.actionHomeFragmentToCardProductFragment(product)
        findNavController().navigate(action)
    }
    private val pagerAdapter by lazy {
        MainScreenPagerAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        setupBottomNavigationView()
        setupRecyclerView()
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

    private fun setupRecyclerView() {
        binding.searchResultRecyclerView.adapter = adapter
        lifecycleScope.launch {
            viewModel.searchResultItems.collect {
                adapter.submitList(it)
            }
        }
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

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean = true
        })
    }
}
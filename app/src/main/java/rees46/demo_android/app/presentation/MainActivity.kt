package rees46.demo_android.app.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.NavHostFragment
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import rees46.demo_android.app.R
import rees46.demo_android.app.databinding.ActivityMainBinding
import rees46.demo_android.feature.Navigator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navigator by lazy {
        get<Navigator> {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            parametersOf(navHostFragment.navController)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        setupTopAppBar()
        setupBottomNavigationView()
        createOptionMenu()
    }

    private fun setupTopAppBar() {
        setSupportActionBar(binding.topAppBar)
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> navigator.navigate(R.id.homeFragment)
                R.id.category -> navigator.navigate(R.id.categoryFragment)
                R.id.cart -> navigator.navigate(R.id.cartFragment)
                R.id.settings -> navigator.navigate(R.id.settingsFragment)
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

                if(menuItem.itemId == R.id.menu_top_app_search) {
                    supportActionBar?.hide()
                    binding.bottomNavigation.visibility = View.GONE
                    navigator.navigate(R.id.searchFragment)
                }

                return false
            }
        })
    }

    override fun onBackPressed() {
        supportActionBar?.show()
        binding.bottomNavigation.visibility = View.VISIBLE

        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}

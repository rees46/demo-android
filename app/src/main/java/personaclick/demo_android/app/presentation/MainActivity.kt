package personaclick.demo_android.app.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import personaclick.demo_android.app.R
import personaclick.demo_android.app.databinding.ActivityMainBinding
import com.personaclick.demo_android.navigation.Navigator

class MainActivity : AppCompatActivity(), LifecycleOwner {

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

        setupNavigator()
        setupTopAppBar()
        setupBottomNavigationView()
        setupPopBackStack()
        createOptionMenu()
    }

    private fun setupTopAppBar() {
        setSupportActionBar(binding.topAppBar)
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> switchBottomTab(R.id.homeFragment)
                R.id.category -> switchBottomTab(R.id.categoryFragment)
                R.id.cart -> switchBottomTab(R.id.cartFragment)
                R.id.settings -> switchBottomTab(R.id.settingsFragment)
            }

            true
        }
    }

    private fun switchBottomTab(id: Int) {
        if(navigator.getCurrentDestinationId() == id) return

        navigator.navigate(id)
    }

    private fun setupNavigator() {
        navigator.addOnDestinationChangedListener { _, destination, _ ->
            if(navigator.getPreviousDestinationId() == R.id.searchFragment) {
                showBars(true)
                return@addOnDestinationChangedListener
            }

            if(destination.id == R.id.searchFragment) {
                showBars(false)
            }
        }
    }

    private fun setupPopBackStack() {
        onBackPressedDispatcher.addCallback(
            owner = this,
            onBackPressedCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if(navigator.getCurrentDestinationId() == R.id.searchFragment) {
                        showBars(true)
                    }

                    navigator.popBackStack()

                    changeSelectedBottomItem()
                }
            }
        )
    }

    private fun changeSelectedBottomItem() {
        with(binding.bottomNavigation) {
            when (navigator.getCurrentDestinationId()) {
                R.id.homeFragment -> selectedItemId = R.id.home
                R.id.categoryFragment -> selectedItemId = R.id.category
                R.id.cartFragment -> selectedItemId = R.id.cart
                R.id.settingsFragment -> selectedItemId = R.id.settingsFragment
            }
        }
    }


    private fun createOptionMenu() {
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_top_app_bar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_top_app_cart -> {
                        binding.bottomNavigation.selectedItemId = R.id.cart
                        true
                    }
                    R.id.menu_top_app_search -> {
                        navigator.navigate(R.id.searchFragment)
                        true
                    }
                    else -> false
                }
            }
        })
    }

    private fun showBars(isShow: Boolean) {
        if(isShow) {
            supportActionBar?.show()
        }
        else {
            supportActionBar?.hide()
        }

        binding.bottomNavigation.isVisible = isShow
    }
}

package rees46.demo_android.features.main.cart

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import rees46.demo_android.core.utils.onBackPressedNavigation
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCartBinding

class CartFragment
    : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    private val viewModel: CartViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        onBackPressedNavigation()
    }
}

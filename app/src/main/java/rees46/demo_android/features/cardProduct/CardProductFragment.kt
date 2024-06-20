package rees46.demo_android.features.cardProduct

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import rees46.demo_android.core.utils.onBackPressedNavigation
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCardProductBinding

class CardProductFragment
    : BaseFragment<FragmentCardProductBinding>(FragmentCardProductBinding::inflate) {

    private val viewModel: CardProductViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        onBackPressedNavigation()
    }
}

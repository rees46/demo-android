package rees46.demo_android.feature.error

import android.os.Bundle
import android.view.View
import rees46.demo_android.feature.BaseFragment
import rees46.demo_android.feature.utils.onBackPressedNavigation
import rees46.demo_android.databinding.FragmentErrorBinding

class ErrorFragment : BaseFragment<FragmentErrorBinding>(FragmentErrorBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedNavigation()
    }
}

package rees46.demo_android.app.ui

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import rees46.demo_android.R
import rees46.demo_android.app.base.BaseFragment
import rees46.demo_android.databinding.FragmentRootBinding

class RootFragment : BaseFragment<FragmentRootBinding>(FragmentRootBinding::inflate) {

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_rootFragment_to_mainFragment)
        }, 1000)
    }
}
package rees46.demo_android

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentStartScreenBinding

class StartScreenFragment
    : BaseFragment<FragmentStartScreenBinding>(FragmentStartScreenBinding::inflate) {

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_startScreenFragment_to_homeFragment)
        }, 3000)
    }
}
package rees46.demo_android.feature.utils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.handleOnBackPressed(onBackPressed: () -> Unit, isEnabledBackButton: Boolean = true) {
    requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(isEnabledBackButton) {
        override fun handleOnBackPressed() {
            onBackPressed.invoke()
        }
    })
}

fun Fragment.onBackPressedNavigation() {
    this.handleOnBackPressed({ findNavController().popBackStack() }, true)
}

fun Fragment.backPressedInvoke() {
    requireActivity().onBackPressedDispatcher.onBackPressed()
}
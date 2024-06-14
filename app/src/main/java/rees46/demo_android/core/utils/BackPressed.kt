package rees46.demo_android.core.utils

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.onBackPressed(onBackPressed: () -> Unit, isEnabledBackButton: Boolean = true) {
    requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(isEnabledBackButton) {
        override fun handleOnBackPressed() {
            onBackPressed.invoke()
        }
    })
}

fun Fragment.backPressedInvoke() {
    requireActivity().onBackPressedDispatcher.onBackPressed()
}
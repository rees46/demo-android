package rees46.demo_android.core.utils

import androidx.fragment.app.Fragment

fun Fragment.backPressedInvoke() {
    requireActivity().onBackPressedDispatcher.onBackPressed()
}

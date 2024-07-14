package rees46.demo_android.utils

import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

internal val Fragment.windowInsetsController: WindowInsetsControllerCompat
    get() = WindowCompat.getInsetsController(
        requireActivity().window,
        requireActivity().window.decorView
    )

fun Fragment.hideSystemBar() {
    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    windowInsetsController.systemBarsBehavior
}

fun Fragment.showSystemBar() {
    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
}

fun Fragment.fragmentOnFullScreen(vararg view: View) {
    view.forEach {
        it.isVisible = false
    }
    hideSystemBar()
}

fun Fragment.fragmentOffFullScreen(vararg view: View) {
    view.forEach {
        it.isVisible = true
    }
    showSystemBar()
}

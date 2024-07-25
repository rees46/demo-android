package rees46.demo_android.core.utils

import android.content.Context
import android.util.DisplayMetrics

object ViewUtils {
    fun convertDpToPixel(
        dp: Float,
        context: Context
    ): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPixelsToDp(
        px: Float,
        context: Context
    ): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}

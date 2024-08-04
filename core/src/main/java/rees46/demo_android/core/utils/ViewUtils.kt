package rees46.demo_android.core.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.annotation.DimenRes

object ViewUtils {

    fun convertDpToPixel(
        dp: Float,
        context: Context
    ): Float =
        dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

    fun convertPixelToDp(
        px: Float,
        context: Context
    ): Float =
        px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

    fun convertResToDp(
        @DimenRes id: Int,
        context: Context
    ) =
        convertPixelToDp(context.resources.getDimension(id), context)
}

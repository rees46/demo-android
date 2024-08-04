package rees46.demo_android.core.utils

import android.content.Context
import android.util.DisplayMetrics
import androidx.annotation.DimenRes

object ViewUtils {

    fun convertDpToPx(
        dp: Float,
        context: Context
    ): Float =
        dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

    fun convertPxToDp(
        px: Float,
        context: Context
    ): Float =
        px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

    fun convertResToDp(
        @DimenRes id: Int,
        context: Context
    ): Float =
        convertPxToDp(context.resources.getDimension(id), context)

    fun convertResToPx(
        @DimenRes id: Int,
        context: Context
    ): Float {
        val dp = convertResToDp(id, context)
        return convertDpToPx(dp, context)
    }
}

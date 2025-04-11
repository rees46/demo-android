package com.personaclick.demo_android.ui.extensions

import android.content.Context
import androidx.annotation.DimenRes

fun Context.convertPxToDp(px: Float): Float {
    return px / resources.displayMetrics.density
}

fun Context.convertDimenResToPx(@DimenRes dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}

fun Context.convertDimenResToDp(@DimenRes dimenRes: Int): Float {
    val px = convertDimenResToPx(dimenRes)
    return convertPxToDp(px)
}

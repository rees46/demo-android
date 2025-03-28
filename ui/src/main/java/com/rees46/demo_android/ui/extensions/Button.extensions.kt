package com.personaclick.demo_android.ui.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.widget.Button
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Button.setBackgroundColor(
    context: Context,
    @ColorRes colorRes: Int
) {
    val color = ContextCompat.getColor(context, colorRes)

    backgroundTintList = ColorStateList.valueOf(color)
}

fun Button.setTextColor(
    context: Context,
    @ColorRes colorRes: Int
) {
    val color = ContextCompat.getColor(context, colorRes)

    setTextColor(ColorStateList.valueOf(color))
}

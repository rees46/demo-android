package com.rees46.demo_android.ui.utils

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.widget.Button
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

object ColorUtils {

    fun getColorList(
        context: Context,
        @ColorRes colorRes: Int
    ): ColorStateList {
        val color = getColor(
            context = context,
            colorRes = colorRes
        )
        return ColorStateList.valueOf(color)
    }

    fun getColor(
        context: Context,
        @ColorRes colorRes: Int
    ) =
        ContextCompat.getColor(context, colorRes)

    fun setBackgroundButtonColor(
        context: Context,
        button: Button,
        @ColorRes colorRes: Int
    ) {
        val color = getColor(context, colorRes)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.backgroundTintList = ColorStateList.valueOf(color)
        } else {
            button.setBackgroundColor(color)
        }
    }

    fun setTextButtonColor(
        context: Context,
        button: Button,
        @ColorRes colorRes: Int
    ) {
        val color = getColor(context, colorRes)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setTextColor(ColorStateList.valueOf(color))
        } else {
            button.setTextColor(color)
        }
    }
}

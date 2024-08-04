package com.rees46.demo_android.ui.button.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.rees46.demo_android.ui.utils.ColorUtils
import com.rees46.ui.R
import rees46.demo_android.core.utils.ViewUtils

class CommonWhiteButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CommonButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    backgroundColorRes = R.color.white,
    textColorRes = R.color.black
) {

    init {
        strokeWidth = ViewUtils.convertResToDp(R.dimen.default_button_strokeWidth, context).toInt()

        strokeColor = ColorUtils.getColorList(
            context = context,
            colorRes = R.color.black
        )
    }
}

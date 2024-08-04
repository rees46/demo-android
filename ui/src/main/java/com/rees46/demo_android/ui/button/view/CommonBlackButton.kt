package com.rees46.demo_android.ui.button.view

import android.content.Context
import android.util.AttributeSet
import com.rees46.ui.R

open class CommonBlackButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    textSizeRes: Int = R.dimen.default_button_textSize,
    widthRes: Int? = null,
) : CommonButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textSizeRes = textSizeRes,
    widthRes = widthRes,
    backgroundColorRes = R.color.black,
    textColorRes = R.color.white
)

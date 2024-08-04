package com.rees46.demo_android.ui.button.view

import android.content.Context
import android.util.AttributeSet
import com.rees46.ui.R

class CommonBlackButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CommonButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    backgroundColorRes = R.color.black,
    textColorRes = R.color.white
)

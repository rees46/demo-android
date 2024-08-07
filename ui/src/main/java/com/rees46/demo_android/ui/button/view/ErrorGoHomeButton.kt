package com.rees46.demo_android.ui.button.view

import android.content.Context
import android.util.AttributeSet
import com.rees46.ui.R

open class ErrorGoHomeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textRes = R.string.go_home,
    backgroundColorRes = R.color.black,
    textColorRes = R.color.white
)

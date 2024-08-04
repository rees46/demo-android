package com.rees46.demo_android.ui.button.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorRes
import com.google.android.material.button.MaterialButton
import com.rees46.demo_android.ui.utils.ColorUtils
import com.rees46.ui.R
import rees46.demo_android.core.utils.ViewUtils

@SuppressLint("ViewConstructor")
open class CommonButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    @ColorRes private val backgroundColorRes: Int,
    @ColorRes private val textColorRes: Int
) : MaterialButton(context, attrs, defStyleAttr)  {

    init {
        setupView()
    }

    private fun setupView() {
        textSize = ViewUtils.convertResToDp(R.dimen.default_button_textSize, context).toFloat()
        setTypeface(null, Typeface.BOLD)
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        gravity = Gravity.CENTER

        setCornerRadiusResource(R.dimen.default_button_cornerRadius)

        ColorUtils.setBackgroundButtonColor(
            context = context,
            button = this,
            colorRes = backgroundColorRes
        )
        ColorUtils.setTextButtonColor(
            context = context,
            button = this,
            colorRes = textColorRes
        )
    }
}

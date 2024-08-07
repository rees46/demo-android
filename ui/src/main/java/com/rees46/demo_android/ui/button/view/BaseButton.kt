package com.rees46.demo_android.ui.button.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.google.android.material.button.MaterialButton
import com.rees46.demo_android.ui.utils.ViewColorUtils
import com.rees46.ui.R
import com.rees46.demo_android.ui.utils.DimensionsConverter

@SuppressLint("ViewConstructor")
open class BaseButton @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null,
    val defStyleAttr: Int = 0,
    private val textSizeRes: Int = R.dimen.default_button_textSize,
    @StringRes private val textRes: Int,
    @ColorRes private val backgroundColorRes: Int,
    @ColorRes private val textColorRes: Int
) : MaterialButton(context, attrs, defStyleAttr)  {

    init {
        setupView()
    }

    private fun setupView() {
        setText(textRes)
        textSize = DimensionsConverter.convertResToDp(textSizeRes, context)
        setTypeface(null, Typeface.BOLD)
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        gravity = Gravity.CENTER

        setCornerRadiusResource(R.dimen.default_button_cornerRadius)

        ViewColorUtils.setBackgroundButtonColor(
            context = context,
            button = this,
            colorRes = backgroundColorRes
        )
        ViewColorUtils.setTextButtonColor(
            context = context,
            button = this,
            colorRes = textColorRes
        )

        strokeWidth = DimensionsConverter.convertResToDp(R.dimen.default_button_strokeWidth, context).toInt()

        strokeColor = ViewColorUtils.getColorList(
            context = context,
            colorRes = R.color.black
        )
    }
}

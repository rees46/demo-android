package com.personaclick.demo_android.ui.button.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.google.android.material.button.MaterialButton
import com.personaclick.demo_android.ui.extensions.convertDimenResToDp
import com.personaclick.demo_android.ui.extensions.setBackgroundColor
import com.personaclick.demo_android.ui.extensions.setTextColor
import com.personaclick.ui.R

@SuppressLint("ViewConstructor")
open class BaseButton @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null,
    val defStyleAttr: Int = 0,
    @DimenRes private val textSizeRes: Int = R.dimen.text_size_default_button,
    @StringRes private val textRes: Int,
    @ColorRes private val backgroundColorRes: Int,
    @ColorRes private val textColorRes: Int
) : MaterialButton(context, attrs, defStyleAttr)  {

    init {
        setupView()
    }

    private fun setupView() {
        setText(textRes)
        textSize = context.convertDimenResToDp(textSizeRes)
        setTypeface(null, Typeface.BOLD)
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        setTextColor(context, textColorRes)

        gravity = Gravity.CENTER

        setCornerRadiusResource(R.dimen.corner_radius_default_button)

        setBackgroundColor(context, backgroundColorRes)

        setStrokeWidthResource(R.dimen.stroke_width_default_button)
        setStrokeColorResource(R.color.color_primary)
    }
}

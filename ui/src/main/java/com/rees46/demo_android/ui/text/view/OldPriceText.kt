package com.rees46.demo_android.ui.text.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import com.rees46.ui.R
import com.rees46.demo_android.ui.utils.DimensionsConverter

@SuppressLint("ViewConstructor")
open class OldPriceText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val textSizeRes: Int = R.dimen.default_oldPrice_textSize
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr)  {

    init {
        setupView()
    }

    private fun setupView() {
        paintFlags += Paint.STRIKE_THRU_TEXT_FLAG
        textSize = DimensionsConverter.convertResToDp(textSizeRes, context)
    }

    fun updateText(value: String) {
        text = value
    }
}

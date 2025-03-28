package com.personaclick.demo_android.ui.text.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.DimenRes
import com.personaclick.demo_android.ui.extensions.convertDimenResToDp
import com.personaclick.ui.R

@SuppressLint("ViewConstructor")
open class OldPriceText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    @DimenRes private val textSizeRes: Int = R.dimen.text_size_default_old_price
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr)  {

    init {
        setupView()
    }

    private fun setupView() {
        paintFlags += Paint.STRIKE_THRU_TEXT_FLAG

        textSize = context.convertDimenResToDp(textSizeRes)
    }

    fun updateText(value: String) {
        text = value
    }
}

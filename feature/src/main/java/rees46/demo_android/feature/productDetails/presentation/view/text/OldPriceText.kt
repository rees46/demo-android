package rees46.demo_android.feature.productDetails.presentation.view.text

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import rees46.demo_android.R

@SuppressLint("ViewConstructor")
class OldPriceText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : com.rees46.demo_android.ui.text.view.OldPriceText(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textSizeRes = R.dimen.productDetails_oldPrice_textSize,
)

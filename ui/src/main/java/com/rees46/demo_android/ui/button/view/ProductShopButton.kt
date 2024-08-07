package com.rees46.demo_android.ui.button.view

import android.content.Context
import android.util.AttributeSet
import com.rees46.ui.R

class ProductShopButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textRes = R.string.shop,
    backgroundColorRes = R.color.black,
    textColorRes = R.color.white,
    textSizeRes = R.dimen.product_shop_button_textSize
)

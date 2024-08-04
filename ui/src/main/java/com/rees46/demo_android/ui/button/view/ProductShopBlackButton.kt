package com.rees46.demo_android.ui.button.view

import android.content.Context
import android.util.AttributeSet
import com.rees46.ui.R

class ProductShopBlackButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CommonBlackButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textSizeRes = R.dimen.product_shop_button_textSize,
    widthRes = R.dimen.product_shop_button_width
)

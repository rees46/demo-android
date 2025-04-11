package com.personaclick.demo_android.ui.button.view

import android.content.Context
import android.util.AttributeSet
import com.personaclick.ui.R

class ProductShopButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textRes = R.string.shop,
    backgroundColorRes = R.color.background_color_opposite_primary,
    textColorRes = R.color.text_color_opposite_primary,
    textSizeRes = R.dimen.text_size_product_shop_button
)

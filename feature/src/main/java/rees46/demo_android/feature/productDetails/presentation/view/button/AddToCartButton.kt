package rees46.demo_android.feature.productDetails.presentation.view.button

import android.content.Context
import android.util.AttributeSet
import com.rees46.demo_android.ui.button.view.BaseButton
import com.rees46.ui.R

open class AddToCartButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textRes = rees46.demo_android.R.string.add_to_cart,
    backgroundColorRes = R.color.black,
    textColorRes = R.color.white
)

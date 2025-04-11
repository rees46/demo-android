package personaclick.demo_android.feature.productDetails.presentation.view.button

import android.content.Context
import android.util.AttributeSet
import com.personaclick.demo_android.ui.button.view.BaseButton
import com.personaclick.ui.R

open class AddToCartButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseButton(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textRes = personaclick.demo_android.R.string.add_to_cart,
    backgroundColorRes = R.color.background_color_opposite_primary,
    textColorRes = R.color.text_color_opposite_primary
)

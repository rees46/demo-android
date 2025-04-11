package personaclick.demo_android.feature.productDetails.presentation.view.text

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import personaclick.demo_android.R

@SuppressLint("ViewConstructor")
class OldPriceText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : com.personaclick.demo_android.ui.text.view.OldPriceText(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
    textSizeRes = R.dimen.text_size_product_details_old_price,
)

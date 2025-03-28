package personaclick.demo_android.feature.products.presentation.view.recyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.personaclick.demo_android.ui.recyclerView.products.view.ProductItemView
import personaclick.demo_android.R

@SuppressLint("ViewConstructor")
class ScrollProductItemView(
    context: Context,
    attrs: AttributeSet? = null
) : ProductItemView(
    context = context,
    attrs = attrs
) {

    override var isShopVisible: Boolean = true
    override var layoutWidthRes: Int = R.dimen.width_products_layout
}

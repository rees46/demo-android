package personaclick.demo_android.feature.recommendationBlock.presentation.view.recyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.personaclick.demo_android.ui.recyclerView.products.view.ProductItemView
import personaclick.demo_android.R

@SuppressLint("ViewConstructor")
class RecommendationProductItemView(
    context: Context,
    attrs: AttributeSet? = null
) : ProductItemView(
    context = context,
    attrs = attrs
) {

    override var isShopVisible: Boolean = false
    override var layoutWidthRes: Int = R.dimen.width_recommendation_block_layout
}

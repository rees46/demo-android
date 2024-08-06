package rees46.demo_android.feature.recommendationBlock.presentation.view.recyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.rees46.demo_android.ui.recyclerView.products.view.ProductItemView

@SuppressLint("ViewConstructor")
class RecommendationProductItemView(
    context: Context,
    attrs: AttributeSet? = null
) : ProductItemView(
    context = context,
    attrs = attrs
) {

    override var isShopVisible: Boolean = false
    override var layoutWidth: Float = 140f
}

package com.rees46.demo_android.ui.recyclerView.products.recommendationBlock.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.rees46.demo_android.ui.recyclerView.products.base.view.adapter.ProductItemView

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

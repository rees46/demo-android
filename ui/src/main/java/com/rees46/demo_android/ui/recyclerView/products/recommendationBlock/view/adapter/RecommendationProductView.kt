package com.rees46.demo_android.ui.recyclerView.products.recommendationBlock.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.rees46.demo_android.ui.recyclerView.products.base.view.adapter.ProductView

@SuppressLint("ViewConstructor")
class RecommendationProductView(
    context: Context,
    attrs: AttributeSet? = null
) : ProductView(
    context = context,
    attrs = attrs
) {

    override var isShopVisible: Boolean = false
    override var layoutWidth: Float = 140f
}

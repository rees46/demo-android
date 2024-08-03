package com.rees46.demo_android.ui.recyclerView.products.scroll.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.rees46.demo_android.ui.recyclerView.products.base.view.adapter.ProductItemView

@SuppressLint("ViewConstructor")
class ScrollProductItemView(
    context: Context,
    attrs: AttributeSet? = null
) : ProductItemView(
    context = context,
    attrs = attrs
) {

    override var isShopVisible: Boolean = true
    override var layoutWidth: Float = 171f
}

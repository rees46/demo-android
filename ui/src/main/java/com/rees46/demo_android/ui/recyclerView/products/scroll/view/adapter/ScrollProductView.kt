package com.rees46.demo_android.ui.recyclerView.products.scroll.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.rees46.demo_android.ui.recyclerView.products.base.view.adapter.ProductView

@SuppressLint("ViewConstructor")
class ScrollProductView(
    context: Context,
    attrs: AttributeSet? = null
) : ProductView(
    context = context,
    attrs = attrs
) {

    override var isShopVisible: Boolean = true
    override var layoutWidth: Float = 171f
}
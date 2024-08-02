package com.rees46.demo_android.ui.recyclerView.products.scroll.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.rees46.demo_android.ui.recyclerView.products.base.view.adapter.ProductView
import rees46.demo_android.core.utils.ViewUtils

@SuppressLint("ViewConstructor")
class ProductView(
    context: Context,
    attrs: AttributeSet? = null
) : ProductView(
    context = context,
    attrs = attrs
) {

    override fun setup() {
        super.setup()

        shopButton.setOnClickListener { }
        shopButton.isVisible = true

        productImageView.updateLayoutParams {
            width = ViewUtils.convertDpToPixel(
                dp = 171f,
                context = context
            ).toInt()
        }
    }
}

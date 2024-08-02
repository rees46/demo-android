package com.rees46.demo_android.ui.recyclerView.products.scroll.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

class ProductsAdapter(
    private val context: Context,
    productItems: List<ProductItem>,
    listener: OnClickListener
) : ItemAdapter<ProductItem, ProductView>(
    items = productItems,
    listener = listener
) {

    override fun createItemView(): ItemView =
        ProductView(
            context = context,
            attrs = null
        ).apply {
            setup()
        }
}

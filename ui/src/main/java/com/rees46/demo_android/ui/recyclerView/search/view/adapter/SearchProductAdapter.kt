package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

class SearchProductAdapter(
    private val context: Context,
    items: List<ProductItem>,
    listener: OnItemClickListener
) : ListItemAdapter<ProductItem, SearchProductItemView>(
    items = items,
    listener = listener
) {

    override fun createItemView(): SearchProductItemView =
        SearchProductItemView(
            context = context
        ).apply {
            setup()
        }
}

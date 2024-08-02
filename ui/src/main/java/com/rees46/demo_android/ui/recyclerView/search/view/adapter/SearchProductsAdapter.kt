package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

class SearchProductsAdapter(
    private val context: Context,
    private val productItems: List<ProductItem>,
    private val listener: OnItemClickListener
) : ListItemAdapter<ProductItem, SearchProductView>(
    context = context,
    items = productItems,
    listener = listener
) {

    override fun submitList(list: MutableList<ProductItem>?) {
        super.submitList(list)
    }

    override fun createItemView(): SearchProductView =
        SearchProductView(
            context = context
        )
}

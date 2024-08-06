package com.rees46.demo_android.ui.recyclerView.products.adapter

import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.products.view.ProductItemView

abstract class ProductsAdapter(
    items: List<ProductRecyclerViewItem>,
    listener: OnItemClickListener
) : ListItemAdapter<ProductRecyclerViewItem, ProductItemView>(
    items = items,
    listener = listener
)

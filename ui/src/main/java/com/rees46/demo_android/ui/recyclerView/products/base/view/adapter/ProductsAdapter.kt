package com.rees46.demo_android.ui.recyclerView.products.base.view.adapter

import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

abstract class ProductsAdapter(
    items: List<ProductItem>,
    listener: OnItemClickListener
) : ListItemAdapter<ProductItem, ProductItemView>(
    items = items,
    listener = listener
)

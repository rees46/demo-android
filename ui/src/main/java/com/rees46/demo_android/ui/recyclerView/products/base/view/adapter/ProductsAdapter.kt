package com.rees46.demo_android.ui.recyclerView.products.base.view.adapter

import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.RecyclerItemAdapter
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

abstract class ProductsAdapter(
    items: List<ProductItem>,
    listener: OnItemClickListener
) : RecyclerItemAdapter<ProductItem, ProductView>(
    items = items,
    listener = listener
)

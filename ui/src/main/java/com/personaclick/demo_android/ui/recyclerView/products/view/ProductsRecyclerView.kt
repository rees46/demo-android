package com.personaclick.demo_android.ui.recyclerView.products.view

import android.content.Context
import android.util.AttributeSet
import com.personaclick.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem

abstract class ProductsRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListRecyclerView<ProductRecyclerViewItem, ProductItemView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
)

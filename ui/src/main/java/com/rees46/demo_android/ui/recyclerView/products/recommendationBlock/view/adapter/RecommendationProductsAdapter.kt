package com.rees46.demo_android.ui.recyclerView.products.recommendationBlock.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import com.rees46.demo_android.ui.recyclerView.products.base.view.adapter.ProductsAdapter

class RecommendationProductsAdapter(
    private val context: Context,
    productItems: List<ProductItem>,
    listener: OnItemClickListener
) : ProductsAdapter(
    items = productItems,
    listener = listener
) {

    override fun createItemView(): ItemView =
        RecommendationProductItemView(
            context = context
        )
}

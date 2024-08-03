package com.rees46.demo_android.ui.recyclerView.cart.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.cart.models.CartProductItem

class CartProductsAdapter(
    private val context: Context,
    cartProductItems: List<CartProductItem>,
    listener: OnItemClickListener
) : ListItemAdapter<CartProductItem, CartProductView>(
    context = context,
    items = cartProductItems,
    listener = listener
) {

    override fun createItemView(): CartProductView =
        CartProductView(
            context = context
        )
}

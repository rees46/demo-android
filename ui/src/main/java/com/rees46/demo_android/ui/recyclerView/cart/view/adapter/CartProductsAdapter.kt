package com.rees46.demo_android.ui.recyclerView.cart.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.cart.models.CartProductItem

class CartProductsAdapter(
    private val context: Context,
    private val cartProductItems: List<CartProductItem>,
    private val listener: OnItemClickListener
) : ListItemAdapter<CartProductItem, CartProductView>(
    context = context,
    items = cartProductItems,
    listener = listener
) {

    override fun submitList(list: MutableList<CartProductItem>?) {
        super.submitList(list)
    }

    override fun createItemView(): CartProductView =
        CartProductView(
            context = context
        )
}

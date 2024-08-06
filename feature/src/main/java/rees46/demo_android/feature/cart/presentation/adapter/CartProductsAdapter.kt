package rees46.demo_android.feature.cart.presentation.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import rees46.demo_android.feature.cart.presentation.models.CartProductRecyclerViewItem
import rees46.demo_android.feature.cart.presentation.view.recyclerView.CartProductItemView

class CartProductsAdapter(
    private val context: Context,
    cartProductItems: List<CartProductRecyclerViewItem>,
    listener: OnItemClickListener
) : ListItemAdapter<CartProductRecyclerViewItem, CartProductItemView>(
    items = cartProductItems,
    listener = listener
) {

    override fun createItemView(): CartProductItemView =
        CartProductItemView(
            context = context
        )
}

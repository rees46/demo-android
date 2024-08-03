package com.rees46.demo_android.ui.recyclerView.cart.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.rees46.demo_android.ui.recyclerView.base.ItemEnum
import com.rees46.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.cart.models.CartProductItem
import com.rees46.demo_android.ui.recyclerView.cart.view.adapter.CartProductView
import com.rees46.demo_android.ui.recyclerView.cart.view.adapter.CartProductsAdapter

class CartProductsRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListRecyclerView<CartProductItem, CartProductView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    private val cartProductItems: MutableList<CartProductItem> = ArrayList()

    override fun createAdapter(
        listener: OnItemClickListener,
        itemEnum: ItemEnum
    ): ListItemAdapter<CartProductItem, CartProductView> =
       CartProductsAdapter(
           context = context,
           cartProductItems = cartProductItems,
           listener = listener
       )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = VERTICAL
            }

    fun updateItems(cartProductItems: List<CartProductItem>) {
        this.cartProductItems.clear()
        this.cartProductItems.addAll(cartProductItems)

        listAdapter?.submitList(cartProductItems)
    }
}

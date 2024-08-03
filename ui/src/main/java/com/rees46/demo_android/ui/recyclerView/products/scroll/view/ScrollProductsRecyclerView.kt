package com.rees46.demo_android.ui.recyclerView.products.scroll.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.view.ProductsRecyclerView
import com.rees46.demo_android.ui.recyclerView.products.scroll.view.adapter.ScrollProductsAdapter

class ScrollProductsRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ProductsRecyclerView(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    override fun createAdapter(listener: OnItemClickListener): ScrollProductsAdapter =
        ScrollProductsAdapter(
            context = context,
            productItems = productItems,
            listener = listener
        )

    override fun createLayoutManager(): LayoutManager =
        GridLayoutManager(context, GRID_LAYOUT_COUNT)

    companion object {

        private const val GRID_LAYOUT_COUNT = 2
    }
}
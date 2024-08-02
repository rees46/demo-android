package com.rees46.demo_android.ui.recyclerView.search.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.rees46.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import com.rees46.demo_android.ui.recyclerView.search.view.adapter.SearchProductView
import com.rees46.demo_android.ui.recyclerView.search.view.adapter.SearchProductsAdapter

class SearchProductsRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListRecyclerView<ProductItem, SearchProductView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {
    val productItems: MutableList<ProductItem> = ArrayList()

    override fun createAdapter(listener: OnItemClickListener): ListItemAdapter<ProductItem, SearchProductView> =
       SearchProductsAdapter(
           context = context,
           productItems = productItems,
           listener = listener
       )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = VERTICAL
            }

    fun updateItems(productItems: List<ProductItem>) {
        this.productItems.clear()
        this.productItems.addAll(productItems)

        listAdapter?.submitList(productItems)
    }
}

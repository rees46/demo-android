package rees46.demo_android.feature.search.presentation.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import rees46.demo_android.feature.search.presentation.view.recyclerView.SearchProductItemView

class SearchProductAdapter(
    private val context: Context,
    items: List<ProductRecyclerViewItem>,
    listener: OnItemClickListener
) : ListItemAdapter<ProductRecyclerViewItem, SearchProductItemView>(
    items = items,
    listener = listener
) {

    override fun createItemView(): SearchProductItemView =
        SearchProductItemView(
            context = context
        )
}

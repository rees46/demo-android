package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.ItemEnum
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener

class SearchResultAdapter<I: Item>(
    private val context: Context,
    private val itemEnum: ItemEnum,
    items: List<I>,
    listener: OnItemClickListener
) : ListItemAdapter<I, ItemView>(
    context = context,
    items = items,
    listener = listener
) {

    override fun createItemView(): ItemView
    {
        val itemView = when(itemEnum) {
            ItemEnum.SEARCH_CATEGORY ->
                SearchCategoryView(
                    context = context
                )
            ItemEnum.SEARCH_PRODUCT ->
                SearchProductView(
                    context = context
                )
        }

        itemView.setup()

        return itemView
    }
}

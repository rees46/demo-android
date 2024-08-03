package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.search.models.CategoryItem

class SearchCategoryAdapter(
    private val context: Context,
    items: List<CategoryItem>,
    listener: OnItemClickListener
) : ListItemAdapter<CategoryItem, SearchCategoryView>(
    context = context,
    items = items,
    listener = listener
) {

    override fun createItemView(): SearchCategoryView =
        SearchCategoryView(
            context = context
        ).apply {
            setup()
        }
}

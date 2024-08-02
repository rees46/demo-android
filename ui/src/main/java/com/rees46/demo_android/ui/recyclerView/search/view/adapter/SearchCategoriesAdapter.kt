package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.search.models.CategoryItem

class SearchCategoriesAdapter(
    private val context: Context,
    private val searchCategoryItems: List<CategoryItem>,
    private val listener: OnItemClickListener
) : ListItemAdapter<CategoryItem, SearchCategoryView>(
    context = context,
    items = searchCategoryItems,
    listener = listener
) {

    override fun submitList(list: MutableList<CategoryItem>?) {
        super.submitList(list)
    }

    override fun createItemView(): SearchCategoryView =
        SearchCategoryView(
            context = context
        )
}

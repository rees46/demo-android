package rees46.demo_android.feature.search.presentation.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import rees46.demo_android.feature.search.presentation.models.CategoryRecyclerViewItem
import rees46.demo_android.feature.search.presentation.view.recyclerView.SearchCategoryItemView

class SearchCategoryAdapter(
    private val context: Context,
    items: List<CategoryRecyclerViewItem>,
    listener: OnItemClickListener
) : ListItemAdapter<CategoryRecyclerViewItem, SearchCategoryItemView>(
    items = items,
    listener = listener
) {

    override fun createItemView(): SearchCategoryItemView =
        SearchCategoryItemView(
            context = context
        )
}

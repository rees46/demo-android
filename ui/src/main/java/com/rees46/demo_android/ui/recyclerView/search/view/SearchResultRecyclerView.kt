package com.rees46.demo_android.ui.recyclerView.search.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.rees46.demo_android.ui.recyclerView.base.ItemEnum
import com.rees46.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.search.models.SearchItem
import com.rees46.demo_android.ui.recyclerView.search.view.adapter.SearchResultAdapter

@SuppressLint("ViewConstructor")
class SearchResultRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListRecyclerView<SearchItem, ItemView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    private val searchItems: MutableList<SearchItem> = ArrayList()

    override fun createAdapter(listener: OnItemClickListener): ListItemAdapter<SearchItem, ItemView> =
        SearchResultAdapter(
           context = context,
           itemEnum = ItemEnum.SEARCH_PRODUCT,
           items = searchItems,
           listener = listener
       )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = VERTICAL
            }

    fun updateItems(searchItems: List<SearchItem>) {
        this.searchItems.clear()
        this.searchItems.addAll(searchItems)

        listAdapter?.submitList(searchItems)
    }
}

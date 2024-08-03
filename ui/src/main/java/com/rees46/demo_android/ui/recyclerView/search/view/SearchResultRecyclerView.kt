package com.rees46.demo_android.ui.recyclerView.search.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.rees46.demo_android.ui.recyclerView.base.ItemEnum
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.search.view.adapter.SearchResultAdapter

@SuppressLint("ViewConstructor")
class SearchResultRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListRecyclerView<Item, ItemView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    private val items: MutableList<Item> = arrayListOf()

    override fun createAdapter(
        listener: OnItemClickListener,
        itemEnum: ItemEnum
    ): ListItemAdapter<Item, ItemView> =
        SearchResultAdapter(
           context = context,
           itemEnum = itemEnum,
           items = items,
           listener = listener
       )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = VERTICAL
            }

    fun updateItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)

        listAdapter?.submitList(items)
    }
}

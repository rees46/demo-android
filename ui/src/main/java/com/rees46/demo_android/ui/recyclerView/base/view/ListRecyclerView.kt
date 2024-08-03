package com.rees46.demo_android.ui.recyclerView.base.view

import android.content.Context
import android.util.AttributeSet
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener

abstract class ListRecyclerView<I: Item, IV: ItemView> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.recyclerview.widget.RecyclerView(context, attrs, defStyleAttr) {

    private var listAdapter: ListItemAdapter<I, IV>? = null

    val items: MutableList<I> = arrayListOf()

    fun setup(
        listener: OnItemClickListener
    ) {
        listAdapter = createAdapter(
            listener = listener
        )

        this.adapter = listAdapter
        this.layoutManager = createLayoutManager()
    }

    abstract fun createAdapter(
        listener: OnItemClickListener
    ): ListItemAdapter<I, IV>

    abstract fun createLayoutManager(): LayoutManager

    fun updateItems(items: List<I>) {
        this.items.clear()
        this.items.addAll(items)

        listAdapter?.submitList(items)
    }
}

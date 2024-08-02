package com.rees46.demo_android.ui.recyclerView

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ItemAdapter<IV: ItemView> (
    private val context: Context,
    private val items: List<Item>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<ItemViewHolder>() {

    interface OnClickListener {
        fun onItemClick(item: Item)
    }

    abstract fun createItemView(): ItemView

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val itemView = createItemView()

        return ItemViewHolder(
            view = itemView,
            listener = listener
        )
    }

    override fun onBindViewHolder(
        viewHolder: ItemViewHolder,
        position: Int
    ) {
        viewHolder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rees46.demo_android.ui.recyclerView.base.models.Item

abstract class ItemAdapter<I: Item, IV: ItemView> (
    private val items: List<I>,
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

package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rees46.demo_android.ui.recyclerView.base.models.Item

abstract class ListItemAdapter<I: Item, IV: ItemView> (
    val items: List<I>,
    private val listener: OnItemClickListener
) : ListAdapter<I, ItemViewHolder>(AsyncDifferConfig.Builder(DiffCallback<I>()).build()) {

    abstract fun createItemView(): ItemView

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val itemView = createItemView()
            .apply {
                setup()
            }

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

    private class DiffCallback<I: Item> : DiffUtil.ItemCallback<I>() {
        override fun areItemsTheSame(
            oldItem: I,
            newItem: I
        ): Boolean = newItem.areItemsTheSame(oldItem)

        override fun areContentsTheSame(
            oldItem: I,
            newItem: I
        ): Boolean = oldItem.areContentsTheSame(newItem)
    }
}

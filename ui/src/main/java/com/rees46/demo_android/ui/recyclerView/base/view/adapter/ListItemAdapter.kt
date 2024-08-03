package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rees46.demo_android.ui.recyclerView.base.models.Item

abstract class ListItemAdapter<I: Item, IV: ItemView> (
    private val context: Context,
    val items: List<I>,
    private val listener: OnItemClickListener
) : ListAdapter<I, ItemViewHolder>(AsyncDifferConfig.Builder(DiffCallback<I>()).build()),
    ItemAdapter<I, IV> {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ItemViewHolder =
        createViewHolder(
            listener = listener
        )

    override fun onBindViewHolder(
        viewHolder: ItemViewHolder,
        position: Int
    ) {
        bindHolder(
            viewHolder = viewHolder,
            position = position
        )
    }

    override fun getItem(position: Int): I =
        items[position]

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

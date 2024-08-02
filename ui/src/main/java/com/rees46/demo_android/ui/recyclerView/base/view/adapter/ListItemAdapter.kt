package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rees46.demo_android.ui.recyclerView.base.models.Item

abstract class ListItemAdapter<I: Item, IV: ItemView> (
    private val context: Context,
    private val items: List<I>,
    private val listener: OnItemClickListener
) : ListAdapter<I, ListItemViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback<I>()).build()
) {

    abstract fun createItemView(): IV

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ListItemViewHolder {
        val itemView = createItemView()
            .apply {
                setup()
            }

        return ListItemViewHolder(
            view = itemView,
            listener = listener
        )
    }

    override fun onBindViewHolder(
        viewHolder: ListItemViewHolder,
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

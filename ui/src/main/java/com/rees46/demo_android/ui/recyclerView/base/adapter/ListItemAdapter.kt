package com.personaclick.demo_android.ui.recyclerView.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.personaclick.demo_android.ui.recyclerView.base.view.RecyclerItemView
import com.personaclick.demo_android.ui.recyclerView.base.view.RecyclerItemViewHolder
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener

abstract class ListItemAdapter<I: RecyclerViewItem, IV: RecyclerItemView> (
    val items: List<I>,
    private val listener: OnItemClickListener
) : ListAdapter<I, RecyclerItemViewHolder>(AsyncDifferConfig.Builder(DiffCallback<I>()).build()) {

    abstract fun createItemView(): RecyclerItemView

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        val itemView = createItemView()
            .apply {
                setup()
            }

        return RecyclerItemViewHolder(
            view = itemView,
            listener = listener
        )
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerItemViewHolder,
        position: Int
    ) {
        viewHolder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private class DiffCallback<I: RecyclerViewItem> : DiffUtil.ItemCallback<I>() {
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

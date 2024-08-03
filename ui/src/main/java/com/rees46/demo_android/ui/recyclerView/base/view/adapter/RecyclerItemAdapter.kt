package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rees46.demo_android.ui.recyclerView.base.models.Item

abstract class RecyclerItemAdapter<I: Item, IV: ItemView> (
    private val items: List<I>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemViewHolder>(),
    ItemAdapter<I, IV>
{

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
}

package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rees46.demo_android.ui.recyclerView.base.models.Item

open class ListItemViewHolder(
    private val view: ItemView,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(view) {

    fun bind(item: Item) {
        view.bind(item, listener)
    }
}

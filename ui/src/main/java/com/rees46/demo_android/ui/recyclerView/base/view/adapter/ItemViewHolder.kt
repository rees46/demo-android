package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem

class ItemViewHolder(
    private val view: ItemView,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(view) {

    fun bind(item: RecyclerViewItem) {
        view.bind(item, listener)
    }
}

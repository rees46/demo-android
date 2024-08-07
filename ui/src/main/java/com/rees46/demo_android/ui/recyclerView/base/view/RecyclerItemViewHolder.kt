package com.rees46.demo_android.ui.recyclerView.base.view

import androidx.recyclerview.widget.RecyclerView
import com.rees46.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem

class RecyclerItemViewHolder(
    private val view: RecyclerItemView,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(view) {

    fun bind(item: RecyclerViewItem) {
        view.bind(item, listener)
    }
}

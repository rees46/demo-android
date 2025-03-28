package com.personaclick.demo_android.ui.recyclerView.base.view

import androidx.recyclerview.widget.RecyclerView
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem

class RecyclerItemViewHolder(
    private val view: RecyclerItemView,
    private val listener: OnItemClickListener
) : RecyclerView.ViewHolder(view) {

    fun bind(item: RecyclerViewItem) {
        view.bind(item, listener)
    }
}

package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemAdapter.OnClickListener

open class ItemViewHolder(
    private val view: ItemView,
    private val listener: OnClickListener
) : RecyclerView.ViewHolder(view) {

    fun bind(item: Item) {
        view.bind(item, listener)
    }
}

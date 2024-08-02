package com.rees46.demo_android.ui.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.rees46.demo_android.ui.recyclerView.ItemAdapter.OnClickListener

open class ItemViewHolder(
    private val view: ItemView,
    private val listener: OnClickListener
) : RecyclerView.ViewHolder(view) {

    fun bind(item: Item) {
        view.bind(item, listener)
    }
}

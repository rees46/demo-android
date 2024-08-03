package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import com.rees46.demo_android.ui.recyclerView.base.models.Item

interface ItemAdapter<I: Item, IV: ItemView> {

    fun createItemView(): ItemView

    fun createViewHolder(
        listener: OnItemClickListener
    ): ItemViewHolder {
        val itemView = createItemView()
            .apply {
                setup()
            }

        return ItemViewHolder(
            view = itemView,
            listener = listener
        )
    }

    fun bindHolder(
        viewHolder: ItemViewHolder,
        position: Int
    ) {
        viewHolder.bind(
            item = getItem(position)
        )
    }

    fun getItem(position: Int): Item
}

package com.rees46.demo_android.ui.recyclerView.search.models

import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem

data class CategoryItem(
    val id: String,
    val name: String,
    val parent: String?,
    val url: String,
    val count: Int
): RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val categoryItem = anotherItem as CategoryItem

        return id == categoryItem.id
    }

    override fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean =
        this == anotherItem
}

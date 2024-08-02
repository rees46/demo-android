package com.rees46.demo_android.ui.recyclerView.search.models

import com.rees46.demo_android.ui.recyclerView.base.models.Item

data class CategoryItem(
    val id: String,
    val name: String,
    val parent: String?,
    val url: String,
    val count: Int
): Item() {

    override fun areItemsTheSame(anotherItem: Item): Boolean {
        val categoryItem = anotherItem as CategoryItem

        return id == categoryItem.id
    }

    override fun areContentsTheSame(anotherItem: Item): Boolean =
        this == anotherItem
}

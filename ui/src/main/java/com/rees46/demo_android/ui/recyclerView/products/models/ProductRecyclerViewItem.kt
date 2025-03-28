package com.personaclick.demo_android.ui.recyclerView.products.models

import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem

data class ProductRecyclerViewItem(
    val id: String,
    val name: String,
    val producerName: String,
    val price: Double?,
    val priceFormatted: String,
    val priceFull: Double?,
    val priceFullFormatted: String?,
    val pictureUrl: String,
    val description: String,
    val rating: Float,
    val sale: Int
) : RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val productItem = anotherItem as ProductRecyclerViewItem

        return id == productItem.id
    }

    override fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean =
        this == anotherItem
}

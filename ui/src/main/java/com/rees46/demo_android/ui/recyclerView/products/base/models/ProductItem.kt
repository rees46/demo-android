package com.rees46.demo_android.ui.recyclerView.products.base.models

import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem

data class ProductItem(
    val id: String,
    val name: String,
    val producerName: String,
    val price: Double?,
    val priceFormatted: String,
    val priceFull: Double?,
    val priceFullFormatted: String?,
    val pictureUrl: String,
    val description: String,
    val rating: Float
) : RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val productItem = anotherItem as ProductItem

        return id == productItem.id
    }

    override fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean =
        this == anotherItem
}

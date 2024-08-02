package com.rees46.demo_android.ui.recyclerView.products.base.models

import android.util.Log
import com.rees46.demo_android.ui.recyclerView.base.models.Item

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
) : Item() {

    override fun areItemsTheSame(anotherItem: Item): Boolean {
        val productItem = anotherItem as ProductItem

        return id == productItem.id
    }

    override fun areContentsTheSame(anotherItem: Item): Boolean =
        this == anotherItem
}

package rees46.demo_android.feature.products.presentation.adapter

import com.rees46.demo_android.ui.recyclerView.Item

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
) : Item()

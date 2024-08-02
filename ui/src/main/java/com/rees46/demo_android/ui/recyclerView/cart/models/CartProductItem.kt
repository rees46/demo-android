package com.rees46.demo_android.ui.recyclerView.cart.models

import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

data class CartProductItem(
    val productItem: ProductItem,
    var quantity: Int
) : Item() {

    override fun areItemsTheSame(anotherItem: Item): Boolean {
        val cartProductItem = anotherItem as CartProductItem

        return productItem.id == cartProductItem.productItem.id
    }

    override fun areContentsTheSame(anotherItem: Item): Boolean =
        this == anotherItem
}

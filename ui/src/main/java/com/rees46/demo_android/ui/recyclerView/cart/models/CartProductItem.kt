package com.rees46.demo_android.ui.recyclerView.cart.models

import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

data class CartProductItem(
    val productItem: ProductItem,
    var quantity: Int
) : RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val cartProductItem = anotherItem as CartProductItem

        return productItem.id == cartProductItem.productItem.id
    }

    override fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean =
        this == anotherItem
}

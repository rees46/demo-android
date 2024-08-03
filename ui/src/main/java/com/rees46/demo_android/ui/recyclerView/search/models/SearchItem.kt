package com.rees46.demo_android.ui.recyclerView.search.models

import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

data class SearchItem(
    val productItem: ProductItem,
    val categoryItem: CategoryItem
): Item() {

    override fun areItemsTheSame(anotherItem: Item): Boolean {
        val searchItem = anotherItem as SearchItem

        return productItem.areItemsTheSame(searchItem.productItem)
                && categoryItem.areItemsTheSame(searchItem.categoryItem)
    }

    override fun areContentsTheSame(anotherItem: Item): Boolean =
        this == anotherItem
}

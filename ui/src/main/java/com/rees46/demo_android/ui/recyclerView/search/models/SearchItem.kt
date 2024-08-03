package com.rees46.demo_android.ui.recyclerView.search.models

import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

data class SearchItem(
    val productItems: List<ProductItem>,
    val categoryItems: List<CategoryItem>
): Item() {

    override fun areItemsTheSame(anotherItem: Item): Boolean {
        val searchItem = anotherItem as SearchItem

        with(searchItem) {
            for (index in 0..productItems.size) {
                if (!productItems[index].areItemsTheSame(productItems[index])
                    || !categoryItems[index].areItemsTheSame(categoryItems[index])) {
                    return false
                }
            }
        }

        return true
    }

    override fun areContentsTheSame(anotherItem: Item): Boolean =
        this == anotherItem
}

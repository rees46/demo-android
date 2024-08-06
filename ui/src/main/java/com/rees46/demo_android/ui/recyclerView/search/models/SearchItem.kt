package com.rees46.demo_android.ui.recyclerView.search.models

import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

data class SearchItem(
    val productItems: List<ProductItem>,
    val categoryItems: List<CategoryItem>
): RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val searchItem = anotherItem as SearchItem

        return areItemsTheSame(productItems, searchItem.productItems)
                && areItemsTheSame(categoryItems, anotherItem.categoryItems)
    }

    override fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean =
        this == anotherItem

    private fun areItemsTheSame(items: List<RecyclerViewItem>, anotherItems: List<RecyclerViewItem>): Boolean {
        for (i in 0..items.size) {
            if(i >= anotherItems.size) return false
            if (!items[i].areItemsTheSame(categoryItems[i])) {
                return false
            }
        }

        return true
    }
}

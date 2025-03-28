package personaclick.demo_android.feature.search.presentation.models

import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem

data class SearchRecyclerViewItem(
    val productItems: List<ProductRecyclerViewItem>,
    val categoryItems: List<CategoryRecyclerViewItem>
): RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val searchItem = anotherItem as SearchRecyclerViewItem

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

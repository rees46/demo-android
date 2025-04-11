package personaclick.demo_android.feature.search.presentation.models

import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem

data class CategoryRecyclerViewItem(
    val id: String,
    val name: String,
    val parent: String?,
    val url: String,
    val count: Int
): RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val categoryItem = anotherItem as CategoryRecyclerViewItem

        return id == categoryItem.id
    }

    override fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean =
        this == anotherItem
}

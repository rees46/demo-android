package personaclick.demo_android.feature.search.presentation.adapter

import android.content.Context
import com.personaclick.demo_android.ui.recyclerView.base.adapter.ListItemAdapter
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import personaclick.demo_android.feature.search.presentation.models.CategoryRecyclerViewItem
import personaclick.demo_android.feature.search.presentation.view.recyclerView.SearchCategoryItemView

class SearchCategoryAdapter(
    private val context: Context,
    items: List<CategoryRecyclerViewItem>,
    listener: OnItemClickListener
) : ListItemAdapter<CategoryRecyclerViewItem, SearchCategoryItemView>(
    items = items,
    listener = listener
) {

    override fun createItemView(): SearchCategoryItemView =
        SearchCategoryItemView(
            context = context
        )
}

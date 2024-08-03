package com.rees46.demo_android.ui.recyclerView.search.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.rees46.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.search.models.CategoryItem
import com.rees46.demo_android.ui.recyclerView.search.view.adapter.SearchCategoryAdapter
import com.rees46.demo_android.ui.recyclerView.search.view.adapter.SearchCategoryView

class SearchCategoriesRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListRecyclerView<CategoryItem, SearchCategoryView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    private val categoryItems: MutableList<CategoryItem> = arrayListOf()

    override fun createAdapter(
        listener: OnItemClickListener
    ): ListItemAdapter<CategoryItem, SearchCategoryView> =
        SearchCategoryAdapter(
           context = context,
           items = categoryItems,
           listener = listener
       )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = VERTICAL
            }

    fun updateCategoryItems(categoryItems: List<CategoryItem>) {
        this.categoryItems.clear()
        this.categoryItems.addAll(categoryItems)

        listAdapter?.submitList(categoryItems)
    }
}

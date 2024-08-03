package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.search.models.CategoryItem
import com.rees46.ui.R

class SearchCategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    private lateinit var categoryNameTextView: TextView

    init {
        inflate(context, R.layout.view_search_category_item, this)
        initViews()
    }

    private fun initViews() {
        categoryNameTextView = findViewById(R.id.category_name)
    }

    override fun setup() {
    }

    override fun bind(item: Item, listener: OnItemClickListener) {
        val categoryItem = item as CategoryItem
        with(categoryItem) {
            categoryNameTextView.text = name

            rootView.setOnClickListener{
                listener.onItemClick(item)
            }
        }
    }
}

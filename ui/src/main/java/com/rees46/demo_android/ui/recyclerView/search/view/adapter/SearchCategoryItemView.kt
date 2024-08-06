package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.search.models.CategoryItem
import com.rees46.ui.databinding.ViewSearchCategoryItemBinding

class SearchCategoryItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {
    private var binding: ViewSearchCategoryItemBinding =
        ViewSearchCategoryItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun bind(item: RecyclerViewItem, listener: OnItemClickListener) {
        with(binding) {
            with(item as CategoryItem) {
                categoryName.text = name

                rootView.setOnClickListener{
                    listener.onItemClick(item)
                }
            }
        }
    }
}

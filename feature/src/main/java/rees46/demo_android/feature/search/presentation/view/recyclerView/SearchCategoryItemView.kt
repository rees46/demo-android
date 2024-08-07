package rees46.demo_android.feature.search.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import rees46.demo_android.databinding.ViewSearchCategoryItemBinding
import rees46.demo_android.feature.search.presentation.models.CategoryRecyclerViewItem

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
            with(item as CategoryRecyclerViewItem) {
                categoryName.text = name

                rootView.setOnClickListener{
                    listener.onItemClick(item)
                }
            }
        }
    }
}

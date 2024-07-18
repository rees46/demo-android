package rees46.demo_android.feature.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.databinding.ViewSearchResultCategoryItemBinding
import rees46.demo_android.feature.search.domain.models.Category

class SearchResultCategoriesAdapter(
    private val onSelectItem: (Category) -> Unit
) :
    ListAdapter<Category, SearchResultCategoriesAdapter.ViewHolder>(
        SearchResultItemCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ViewSearchResultCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).apply(holder::bind)
    }

    inner class ViewHolder(private val binding: ViewSearchResultCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                categoryName.text = category.name
                root.setOnClickListener {
                    onSelectItem.invoke(category)
                }
            }
        }
    }

    internal class SearchResultItemCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

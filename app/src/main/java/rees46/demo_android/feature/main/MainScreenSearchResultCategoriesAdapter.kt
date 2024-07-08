package rees46.demo_android.feature.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.personalizatio.api.entities.search.CategoryEntity
import rees46.demo_android.databinding.SearchResultCategoryItemBinding

class MainScreenSearchResultCategoriesAdapter(
    private val onSelectItem: (CategoryEntity) -> Unit
) :
    ListAdapter<CategoryEntity, MainScreenSearchResultCategoriesAdapter.ViewHolder>(SearchResultItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            SearchResultCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).apply(holder::bind)
    }

    inner class ViewHolder(private val binding: SearchResultCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryEntity) {
            binding.categoryName.text = category.name
            binding.root.setOnClickListener {
                onSelectItem.invoke(category)
            }
        }
    }

    internal class SearchResultItemCallback : DiffUtil.ItemCallback<CategoryEntity>() {
        override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

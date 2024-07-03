package rees46.demo_android.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rees46.demo_android.databinding.SearchResultItemBinding
import rees46.demo_android.entity.productsEntity.ProductEntity

class MainScreenSearchResultAdapter(
    private val onSelectItem: (ProductEntity) -> Unit
) :
    ListAdapter<ProductEntity, MainScreenSearchResultAdapter.ViewHolder>(SearchResultItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            SearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).apply(holder::bind)
    }

    inner class ViewHolder(private val binding: SearchResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductEntity) {
            binding.itemNameTextView.text = product.name
            binding.itemPriceTextView.text = product.priceFormatted
            binding.root.setOnClickListener {
                onSelectItem.invoke(product)
            }
            Glide.with(binding.root.context)
                .load(product.pictureUrl)
                .fitCenter()
                .into(binding.previewImg)
        }
    }

    internal class SearchResultItemCallback : DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
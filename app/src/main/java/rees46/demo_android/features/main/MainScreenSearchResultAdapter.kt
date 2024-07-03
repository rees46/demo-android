package rees46.demo_android.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rees46.demo_android.databinding.SearchResultItemBinding
import rees46.demo_android.features.product.Product

class MainScreenSearchResultAdapter(
    private val onSelectItem: (Product) -> Unit
) :
    ListAdapter<Product, MainScreenSearchResultAdapter.ViewHolder>(SearchResultItemCallback()) {

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

        fun bind(product: Product) {
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

    internal class SearchResultItemCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
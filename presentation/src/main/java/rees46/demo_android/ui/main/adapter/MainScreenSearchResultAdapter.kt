package rees46.demo_android.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.utils.ImageUtils
import rees46.demo_android.databinding.SearchResultItemBinding
import rees46.demo_android.domain.entities.ProductDto

class MainScreenSearchResultAdapter(
    private val onSelectItem: (ProductDto) -> Unit
) :
    ListAdapter<ProductDto, MainScreenSearchResultAdapter.ViewHolder>(SearchResultItemCallback()) {

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

        fun bind(product: ProductDto) {
            binding.productName.text = product.name
            binding.price.text = product.priceFormatted
            binding.root.setOnClickListener {
                onSelectItem.invoke(product)
            }
            ImageUtils.updateImage(binding.root, binding.image, product.pictureUrl)
        }
    }

    internal class SearchResultItemCallback : DiffUtil.ItemCallback<ProductDto>() {
        override fun areItemsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductDto, newItem: ProductDto): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

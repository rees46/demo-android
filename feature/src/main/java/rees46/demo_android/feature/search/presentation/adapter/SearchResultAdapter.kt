package rees46.demo_android.feature.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.core.utils.ImageUtils
import rees46.demo_android.databinding.ViewSearchResultItemBinding
import rees46.demo_android.feature.productDetails.domain.models.ProductDto

class SearchResultAdapter(
    private val onSelectItem: (ProductDto) -> Unit
) :
    ListAdapter<ProductDto, SearchResultAdapter.ViewHolder>(SearchResultItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ViewSearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).apply(holder::bind)
    }

    inner class ViewHolder(private val binding: ViewSearchResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductDto) {
            binding.apply {
                productName.text = product.name
                price.text = product.priceFormatted
                root.setOnClickListener {
                    onSelectItem.invoke(product)
                }
                ImageUtils.updateImage(root, image, product.pictureUrl)
            }
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

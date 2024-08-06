package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.rees46.demo_android.ui.extensions.updateImage
import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import com.rees46.ui.databinding.ViewSearchProductItemBinding
class SearchProductItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    private var binding: ViewSearchProductItemBinding =
        ViewSearchProductItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun bind(item: RecyclerViewItem, listener: OnItemClickListener) {
        with(binding) {
            with(item as ProductRecyclerViewItem) {
                productNameText.text = name
                priceText.text = priceFormatted
                productImageView.updateImage(pictureUrl)

                rootView.setOnClickListener{
                    listener.onItemClick(item)
                }
            }
        }
    }
}

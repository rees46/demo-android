package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import com.rees46.ui.databinding.ViewSearchProductItemBinding
import rees46.demo_android.core.utils.updateImage

class SearchProductItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    private var binding: ViewSearchProductItemBinding =
        ViewSearchProductItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun bind(item: Item, listener: OnItemClickListener) {
        with(binding) {
            with(item as ProductItem) {
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

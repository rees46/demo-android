package com.rees46.demo_android.ui.recyclerView.cart.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.cart.models.CartProductItem
import com.rees46.ui.databinding.ViewCartProductItemBinding
import rees46.demo_android.core.utils.updateImage

class CartProductItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    private var binding: ViewCartProductItemBinding =
        ViewCartProductItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun setup() {
    }

    override fun bind(item: Item, listener: OnItemClickListener) {
        with(binding) {
            removeButton.setOnClickListener {
                listener.onItemClick(item)
            }

            val cartProductItem = item as CartProductItem

            with(cartProductItem.productItem) {
                productImageView.updateImage(pictureUrl)

                productNameText.text = name
                producerNameText.text = producerName
                priceText.text = (price?.times(cartProductItem.quantity)).toString()
                productQuantity.text = "x${cartProductItem.quantity}"
            }
        }
    }
}
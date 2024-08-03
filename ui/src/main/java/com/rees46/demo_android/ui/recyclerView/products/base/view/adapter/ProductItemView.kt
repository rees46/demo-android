package com.rees46.demo_android.ui.recyclerView.products.base.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import com.rees46.ui.databinding.ViewProductItemBinding
import rees46.demo_android.core.utils.ViewUtils
import rees46.demo_android.core.utils.updateImage

@SuppressLint("ViewConstructor")
abstract class ProductItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    abstract var isShopVisible: Boolean
    abstract var layoutWidth: Float

    private var binding: ViewProductItemBinding =
        ViewProductItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun setup() {
        with(binding) {
            oldPriceText.paintFlags += Paint.STRIKE_THRU_TEXT_FLAG

            shopButton.root.isVisible = isShopVisible

            productImage.updateLayoutParams {
                width = ViewUtils.convertDpToPixel(
                    dp = layoutWidth,
                    context = context
                ).toInt()
            }
        }
    }

    override fun bind(item: Item, listener: OnItemClickListener) {
        setOnClickListener {
            listener.onItemClick(item)
        }

        val productItem = item as ProductItem

        with(binding) {
            with(productItem) {
                productImage.updateImage(pictureUrl)

                productNameText.text = name
                producerNameText.text = producerName
                priceText.text = priceFormatted
                productRatingBar.rating = rating
            }
        }
    }
}

package com.rees46.demo_android.ui.recyclerView.products.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.rees46.demo_android.ui.extensions.updateImage
import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.base.view.RecyclerItemView
import com.rees46.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import com.rees46.demo_android.ui.utils.DimensionsConverter
import com.rees46.ui.databinding.ViewProductItemBinding

@SuppressLint("ViewConstructor")
abstract class ProductItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerItemView(
    context = context,
    attrs = attrs
) {

    abstract var isShopVisible: Boolean
    abstract var layoutWidth: Float

    private var binding: ViewProductItemBinding =
        ViewProductItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun setup() {
        with(binding) {
            shopButton.isVisible = isShopVisible

            productImage.updateLayoutParams {
                width = DimensionsConverter.convertDpToPx(
                    dp = layoutWidth,
                    context = context
                ).toInt()
            }
        }
    }

    override fun bind(item: RecyclerViewItem, listener: OnItemClickListener) {
        setOnClickListener {
            listener.onItemClick(item)
        }

        val productItem = item as ProductRecyclerViewItem

        with(binding) {
            with(productItem) {
                productImage.updateImage(pictureUrl)

                productNameText.text = name
                producerNameText.text = producerName
                priceText.text = priceFormatted
                productRatingBar.rating = rating
                oldPriceText.updateText(priceFullFormatted.toString())
            }
        }
    }
}
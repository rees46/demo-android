package rees46.demo_android.feature.productDetails.presentation.view.description

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import rees46.demo_android.databinding.ViewProductDetailsDescriptionBinding
import rees46.demo_android.feature.productDetails.domain.models.Product

class ProductDetailsDescriptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

   private var binding: ViewProductDetailsDescriptionBinding =
        ViewProductDetailsDescriptionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.apply {
            oldPriceText.paintFlags += Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    fun setProduct(product: Product) {
        binding.apply {
            productNameText.text = product.name
            producerNameText.text = product.producerName
            priceText.text = product.priceFormatted
            oldPriceText.text = product.priceFullFormatted
            descriptionText.text = product.description
            productRatingBar.rating = product.rating
        }
    }
}

package personaclick.demo_android.feature.productDetails.presentation.view.description

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import personaclick.demo_android.databinding.ViewProductDetailsDescriptionBinding
import personaclick.demo_android.feature.productDetails.domain.models.Product

class ProductDetailsDescriptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

   private var binding: ViewProductDetailsDescriptionBinding =
        ViewProductDetailsDescriptionBinding.inflate(LayoutInflater.from(context), this, true)

    fun setProduct(product: Product) {
        binding.apply {
            productNameText.text = product.name
            producerNameText.text = product.producerName
            priceText.text = product.priceFormatted
            descriptionText.text = product.description
            productRatingBar.rating = product.rating
            oldPriceText.updateText(product.priceFullFormatted.toString())
            saleCardView.setValue(product.sale)
        }
    }
}

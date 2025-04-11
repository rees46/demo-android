package personaclick.demo_android.feature.productDetails.presentation.view.description

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import personaclick.demo_android.databinding.ViewProductDetailsSaleCardBinding

class ProductDetailsSaleCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

   private var binding: ViewProductDetailsSaleCardBinding =
       ViewProductDetailsSaleCardBinding.inflate(LayoutInflater.from(context), this, true)

    fun setValue(value: Int) {
        binding.apply {
            valueText.text = "-${value}%"
        }
    }
}

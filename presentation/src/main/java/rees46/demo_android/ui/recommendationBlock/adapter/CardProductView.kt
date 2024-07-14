package rees46.demo_android.ui.recommendationBlock.adapter

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import rees46.demo_android.R
import rees46.demo_android.utils.ImageUtils
import rees46.demo_android.domain.entities.ProductDto

class CardProductView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private lateinit var productImageView: ImageView
    internal lateinit var producerNameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var oldPriceTextView: TextView
    private lateinit var priceTextView: TextView

    init {
        inflate(context, R.layout.view_recommendation_card_product, this)

        initViews()
    }

    private fun initViews() {
        productImageView = findViewById(R.id.product_image)
        producerNameTextView = findViewById(R.id.producer_name_text)
        productNameTextView = findViewById(R.id.product_name_text)
        ratingBar = findViewById(R.id.product_rating_bar)
        oldPriceTextView = findViewById(R.id.old_price_text)
        priceTextView = findViewById(R.id.price_text)
    }

    internal fun updateProduct(product: ProductDto) {
        ImageUtils.updateImage(this, productImageView, product.pictureUrl)

        productNameTextView.text = product.name
        producerNameTextView.text = product.producerName
        priceTextView.text = product.priceFormatted
    }
}

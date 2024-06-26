package rees46.demo_android.base

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import rees46.demo_android.R
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.recommendationBlock.CardProductViewSettings

abstract class BaseCardProductView @JvmOverloads constructor(
    context: Context,
    private val cardProductViewSettings: CardProductViewSettings,
    attrs: AttributeSet? = null,
    layoutId: Int
) : ConstraintLayout(context, attrs) {

    private lateinit var productImageView: ImageView
    private lateinit var producerNameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var oldPriceTextView: TextView
    private lateinit var priceTextView: TextView

    init {
        inflate(context, layoutId, this)

        initViews()
        setupViews()
    }

    private fun initViews() {
        productImageView = findViewById(R.id.product_image)
        producerNameTextView = findViewById(R.id.producer_name_text)
        productNameTextView = findViewById(R.id.product_name_text)
        ratingBar = findViewById(R.id.product_rating_bar)
        oldPriceTextView = findViewById(R.id.old_price_text)
        priceTextView = findViewById(R.id.price_text)
    }

    private fun setupViews() {
        producerNameTextView.setTextColor(cardProductViewSettings.producerNameTextColor)
    }

    internal fun updateProduct(product: Product) {
        updateImage(product.pictureUrl)

        productNameTextView.text = product.name
        producerNameTextView.text = product.producerName
        priceTextView.text = product.price
    }

    private fun updateImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.loading)
            .error(R.drawable.loading)
            .centerCrop()
            .into(productImageView)
    }
}
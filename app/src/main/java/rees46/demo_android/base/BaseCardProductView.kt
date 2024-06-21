package rees46.demo_android.base

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import rees46.demo_android.R
import rees46.demo_android.features.recommendationBlock.CardProductViewSettings

abstract class BaseCardProductView @JvmOverloads constructor(
    context: Context,
    private val cardProductViewSettings: CardProductViewSettings,
    attrs: AttributeSet? = null,
    layoutId: Int
) : ConstraintLayout(context, attrs) {

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
        producerNameTextView = findViewById(R.id.producer_name_text)
        productNameTextView = findViewById(R.id.product_name_text)
        ratingBar = findViewById(R.id.product_rating_bar)
        oldPriceTextView = findViewById(R.id.old_price_text)
        priceTextView = findViewById(R.id.price_text)
    }

    private fun setupViews() {
        producerNameTextView.setTextColor(cardProductViewSettings.producerNameTextColor)
    }
}
package rees46.demo_android.core_ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import com.google.android.material.button.MaterialButton
import rees46.demo_android.R
import rees46.demo_android.base.BaseCardProductView

class CardProductView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BaseCardProductView(context, attrs, R.layout.view_card_product) {

    private lateinit var producerNameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var reviewsTextView: TextView
    private lateinit var oldPriceTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var saleTextView: TextView
    private lateinit var minusButton: ImageButton
    private lateinit var plusButton: ImageButton
    private lateinit var countInCartTextView: TextView
    private lateinit var addToCartButton: MaterialButton

    private fun initViews() {
        producerNameTextView = findViewById(R.id.producer_name_text)
        productNameTextView = findViewById(R.id.product_name_text)
        descriptionTextView = findViewById(R.id.description_text)
        ratingBar = findViewById(R.id.product_rating_bar)
        reviewsTextView = findViewById(R.id.reviews_text)
        oldPriceTextView = findViewById(R.id.old_price_text)
        priceTextView = findViewById(R.id.price_text)
        saleTextView = findViewById(R.id.sale_text)
        minusButton = findViewById(R.id.minus_button)
        plusButton = findViewById(R.id.plus_button)
        countInCartTextView = findViewById(R.id.count_in_cart_text)
        addToCartButton = findViewById(R.id.add_to_cart_button)
    }

    private fun setupViews() {
    }
}
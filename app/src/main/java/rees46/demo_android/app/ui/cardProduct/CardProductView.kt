package rees46.demo_android.app.ui.cardProduct

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import rees46.demo_android.R
import rees46.demo_android.app.utils.ImageUtils
import rees46.demo_android.domain.entities.ProductEntity

class CardProductView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private lateinit var productImageView: ImageView
    private lateinit var producerNameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var oldPriceTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var reviewsTextView: TextView
    private lateinit var saleTextView: TextView
    private lateinit var minusButton: ImageButton
    private lateinit var plusButton: ImageButton
    private lateinit var countInCartTextView: TextView
    private lateinit var addToCartButton: MaterialButton

    init {
        inflate(context, R.layout.view_card_product, this)
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
        descriptionTextView = findViewById(R.id.description_text)
        reviewsTextView = findViewById(R.id.reviews_text)
        saleTextView = findViewById(R.id.sale_text)
        minusButton = findViewById(R.id.minus_button)
        plusButton = findViewById(R.id.plus_button)
        countInCartTextView = findViewById(R.id.count_in_cart_text)
        addToCartButton = findViewById(R.id.add_to_cart_button)
    }

    private fun setupViews() {
        oldPriceTextView.paintFlags += Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun setupCartAction(onCartActionClick: (CardAction) -> Unit) {
        addToCartButton.setOnClickListener { onCartActionClick.invoke(CardAction.ADD) }
        minusButton.setOnClickListener { onCartActionClick.invoke(CardAction.DECREASE) }
        plusButton.setOnClickListener { onCartActionClick.invoke(CardAction.INCREASE) }
    }

    fun updateProduct(product: ProductEntity) {
        ImageUtils.updateImage(this, productImageView, product.pictureUrl)

        productNameTextView.text = product.name
        producerNameTextView.text = product.producerName
        priceTextView.text = product.priceFormatted
        oldPriceTextView.text = product.priceFullFormatted
        descriptionTextView.text = product.description
    }

    internal fun updateCount(count: Int) {
        countInCartTextView.text = count.toString()
    }
}

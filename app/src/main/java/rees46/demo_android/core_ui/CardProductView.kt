package rees46.demo_android.core_ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import rees46.demo_android.R
import rees46.demo_android.base.BaseCardProductView
import rees46.demo_android.features.product.Product

class CardProductView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseCardProductView(context, attrs, R.layout.view_card_product) {

    private lateinit var descriptionTextView: TextView
    private lateinit var reviewsTextView: TextView
    private lateinit var saleTextView: TextView
    private lateinit var minusButton: ImageButton
    private lateinit var plusButton: ImageButton
    private lateinit var countInCartTextView: TextView
    private lateinit var addToCartButton: MaterialButton

    init {
        initViews()
    }

    private fun initViews() {
        descriptionTextView = findViewById(R.id.description_text)
        reviewsTextView = findViewById(R.id.reviews_text)
        saleTextView = findViewById(R.id.sale_text)
        minusButton = findViewById(R.id.minus_button)
        plusButton = findViewById(R.id.plus_button)
        countInCartTextView = findViewById(R.id.count_in_cart_text)
        addToCartButton = findViewById(R.id.add_to_cart_button)
    }

    override fun updateProduct(product: Product) {
        super.updateProduct(product)

        descriptionTextView.text = product.description
    }
}
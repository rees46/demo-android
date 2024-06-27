package rees46.demo_android.features.main.cart

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import rees46.demo_android.R
import rees46.demo_android.core.utils.ImageUtils
import rees46.demo_android.features.product.Product

class CartProductView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private lateinit var productImageView: ImageView
    private lateinit var producerNameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var priceTextView: TextView

    init {
        inflate(context, R.layout.view_cart_product, this)

        initViews()
        setupViews()
    }

    private fun initViews() {
        productImageView = findViewById(R.id.product_image_view)
        producerNameTextView = findViewById(R.id.producer_name_text)
        productNameTextView = findViewById(R.id.product_name_text)
        priceTextView = findViewById(R.id.price_text)
    }

    private fun setupViews() {
    }

    internal fun updateProduct(product: Product) {
        ImageUtils.updateImage(this, productImageView, product.pictureUrl)

        productNameTextView.text = product.name
        producerNameTextView.text = product.producerName
        priceTextView.text = product.priceFormatted
    }
}

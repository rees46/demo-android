package rees46.demo_android.features.main.cart

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import rees46.demo_android.R
import rees46.demo_android.core.utils.ImageUtils

class CartProductView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    interface ClickListener {
        fun removeProduct(cartProduct: CartProduct)
    }

    private lateinit var productImageView: ImageView
    private lateinit var producerNameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var removeButton: ImageButton

    private var listener: ClickListener? = null

    private lateinit var cartProduct: CartProduct

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
        removeButton = findViewById(R.id.remove_button)
    }

    private fun setupViews() {
        removeButton.setOnClickListener { listener?.removeProduct(cartProduct)
        }
    }

    internal fun updateCartProduct(cartProduct: CartProduct) {
        this.cartProduct = cartProduct

        ImageUtils.updateImage(this, productImageView, cartProduct.product.pictureUrl)

        productNameTextView.text = cartProduct.product.name
        producerNameTextView.text = cartProduct.product.producerName
        priceTextView.text = cartProduct.product.priceFormatted
    }

    internal fun setListener(listener: ClickListener) {
        this.listener = listener
    }
}
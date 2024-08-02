package rees46.demo_android.feature.products.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.rees46.demo_android.ui.recyclerView.Item
import com.rees46.demo_android.ui.recyclerView.ItemAdapter
import com.rees46.demo_android.ui.recyclerView.ItemView
import rees46.demo_android.R
import rees46.demo_android.core.utils.updateImage

@SuppressLint("ViewConstructor")
class ProductView(
    context: Context,
    private val productViewSettings: ProductViewSettings,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    private lateinit var productImageView: ImageView
    private lateinit var producerNameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var oldPriceTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var shopButton: Button

    init {
        inflate(context, R.layout.view_product_item, this)

        initViews()
        setupView()
    }

    private fun initViews() {
        productImageView = findViewById(R.id.product_image)
        producerNameTextView = findViewById(R.id.producer_name_text)
        productNameTextView = findViewById(R.id.product_name_text)
        ratingBar = findViewById(R.id.product_rating_bar)
        oldPriceTextView = findViewById(R.id.old_price_text)
        priceTextView = findViewById(R.id.price_text)
        shopButton = findViewById(R.id.shop_button)
    }

    private fun setupView() {
        oldPriceTextView.paintFlags += Paint.STRIKE_THRU_TEXT_FLAG

        shopButton.setOnClickListener { }

        shopButton.isVisible = productViewSettings.showButton
        productImageView.updateLayoutParams {
            width = productViewSettings.width
        }
    }

    override fun bind(item: Item, listener: ItemAdapter.OnClickListener) {
       setOnClickListener {
            listener.onItemClick(item)
        }

        with(item as ProductItem) {
            productImageView.updateImage(pictureUrl)

            productNameTextView.text = name
            producerNameTextView.text = producerName
            priceTextView.text = priceFormatted
            ratingBar.rating = rating
        }
    }
}

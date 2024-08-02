package com.rees46.demo_android.ui.recyclerView.cart.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.cart.models.CartProductItem
import com.rees46.ui.R
import rees46.demo_android.core.utils.updateImage

class CartProductView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    private lateinit var productImageView: ImageView
    private lateinit var producerNameTextView: TextView
    private lateinit var productNameTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var removeButton: ImageButton
    private lateinit var quantityTextView: TextView

    private lateinit var cartProductItem: CartProductItem

    init {
        inflate(context, R.layout.view_cart_product_item, this)
        initViews()
    }

    private fun initViews() {
        productImageView = findViewById(R.id.product_image_view)
        producerNameTextView = findViewById(R.id.producer_name_text)
        productNameTextView = findViewById(R.id.product_name_text)
        priceTextView = findViewById(R.id.price_text)
        removeButton = findViewById(R.id.remove_button)
        quantityTextView = findViewById(R.id.product_quantity)
    }

    override fun setup() {
    }

    override fun bind(item: Item, listener: OnItemClickListener) {
        removeButton.setOnClickListener {
            listener.onItemClick(item)
        }

        this.cartProductItem = item as CartProductItem

        with(cartProductItem.productItem) {
            productImageView.updateImage(pictureUrl)

            productNameTextView.text = name
            producerNameTextView.text = producerName
            priceTextView.text = (price?.times(cartProductItem.quantity)).toString()
            quantityTextView.text = "x${cartProductItem.quantity}"
        }
    }
}

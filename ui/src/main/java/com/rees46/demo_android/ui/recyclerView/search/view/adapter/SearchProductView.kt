package com.rees46.demo_android.ui.recyclerView.search.view.adapter

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import com.rees46.ui.R
import rees46.demo_android.core.utils.updateImage

class SearchProductView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    private lateinit var productImageView: ImageView
    private lateinit var productNameTextView: TextView
    private lateinit var priceTextView: TextView

    init {
        inflate(context, R.layout.view_search_product_item, this)
        initViews()
    }

    private fun initViews() {
        productImageView = findViewById(R.id.product_image_view)
        productNameTextView = findViewById(R.id.product_name_text)
        priceTextView = findViewById(R.id.price_text)
    }

    override fun setup() {
    }

    override fun bind(item: Item, listener: OnItemClickListener) {
        with(item as ProductItem) {
            productNameTextView.text = name
            priceTextView.text = priceFormatted
            productImageView.updateImage(pictureUrl)

            rootView.setOnClickListener{
                listener.onItemClick(item)
            }
        }
    }
}

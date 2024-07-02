package rees46.demo_android.features.main.category

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import rees46.demo_android.R
import rees46.demo_android.base.BaseCardProductView

class CardProductView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseCardProductView(context, attrs, R.layout.view_category_card_product) {

    interface ClickListener {
        fun shop(productId: String)
    }

    private var listener: ClickListener? = null

    private lateinit var shopButton: MaterialButton

    init {
        initViews()
        setupViews()
    }

    private fun initViews() {
        shopButton = findViewById(R.id.shop_button)
    }

    private fun setupViews() {
        shopButton.setOnClickListener { listener?.shop(product.id) }
    }

    internal fun setListener(listener: ClickListener) {
        this.listener = listener
    }
}

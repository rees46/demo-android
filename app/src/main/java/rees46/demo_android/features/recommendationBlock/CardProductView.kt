package rees46.demo_android.features.recommendationBlock

import android.content.Context
import android.util.AttributeSet
import rees46.demo_android.R
import rees46.demo_android.core_ui.BaseCardProductView

class CardProductView @JvmOverloads constructor(
    context: Context,
    private val cardProductViewSettings: CardProductViewSettings,
    attrs: AttributeSet? = null
) : BaseCardProductView(context, attrs, R.layout.view_recommendation_card_product) {

    init {
        setupViews()
    }

    private fun setupViews() {
        producerNameTextView.setTextColor(cardProductViewSettings.producerNameTextColor)
    }
}
package rees46.demo_android.core_ui

import android.content.Context
import android.util.AttributeSet
import rees46.demo_android.R
import rees46.demo_android.base.BaseCardProductView
import rees46.demo_android.features.recommendationBlock.CardProductViewSettings

class ShortCardProductView @JvmOverloads constructor(
    context: Context,
    private val cardProductViewSettings: CardProductViewSettings,
    attrs: AttributeSet? = null
) : BaseCardProductView(context, attrs, R.layout.view_short_card_product) {

    init {
        setupViews()
    }

    private fun setupViews() {
        producerNameTextView.setTextColor(cardProductViewSettings.producerNameTextColor)
    }
}
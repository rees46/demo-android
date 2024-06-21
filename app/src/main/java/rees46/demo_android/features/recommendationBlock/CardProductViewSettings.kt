package rees46.demo_android.features.recommendationBlock

import android.content.res.TypedArray
import androidx.annotation.ColorRes
import rees46.demo_android.R

class CardProductViewSettings(attrs: TypedArray) {

    private @ColorRes val defaultProducerNameTextColor : Int = R.color.recommendationBlock_cardProduct_producerName_textColor

    internal var producerNameTextColor: Int

    init {
        producerNameTextColor = attrs.getColor(R.styleable.RecommendationBlockView_cardProduct_producerName_textColor, defaultProducerNameTextColor)
    }
}
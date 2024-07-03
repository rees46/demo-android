package rees46.demo_android.features.recommendationBlock

import android.content.res.Resources
import android.content.res.TypedArray
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import rees46.demo_android.R
import rees46.demo_android.utils.ViewAttrsUtils

class RecommendationBlockViewSettings(resources: Resources, attrs: TypedArray) {

    @ColorRes
    private val defaultHeaderTextColor : Int = R.color.recommendationBlock_header_textColor
    @DimenRes
    private val defaultHeaderTextSize : Int = R.dimen.recommendationBlock_header_textSize
    @ColorRes
    private val defaultShowAllTextColor : Int = R.color.recommendationBlock_showAll_textColor
    @DimenRes
    private val defaultShowAllTextSize : Int = R.dimen.recommendationBlock_showAll_textSize

    internal var headerTextColor: Int =
        attrs.getColor(R.styleable.RecommendationBlockView_header_textColor, defaultHeaderTextColor)
    internal var headerTextSize: Float = ViewAttrsUtils.getTextSize(resources, attrs, R.styleable.RecommendationBlockView_header_textSize, defaultHeaderTextSize)
    internal var showAllTextColor: Int = attrs.getColor(R.styleable.RecommendationBlockView_showAll_textColor, defaultShowAllTextColor)
    internal var showAllTextSize: Float = ViewAttrsUtils.getTextSize(resources, attrs, R.styleable.RecommendationBlockView_showAll_textSize, defaultShowAllTextSize)

    internal var cardProductViewSettings: CardProductViewSettings = CardProductViewSettings(attrs)
}
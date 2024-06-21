package rees46.demo_android.features.recommendationBlock

import android.content.res.Resources
import android.content.res.TypedArray
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import rees46.demo_android.R
import rees46.demo_android.utils.ViewAttrsUtils

class RecommendationBlockViewSettings(resources: Resources, attrs: TypedArray) {

    private @ColorRes val defaultHeaderTextColor : Int = R.color.recommendationBlock_header_textColor
    private @DimenRes val defaultHeaderTextSize : Int = R.dimen.recommendationBlock_header_textSize
    private @ColorRes val defaultShowAllTextColor : Int = R.color.recommendationBlock_showAll_textColor
    private @DimenRes val defaultShowAllTextSize : Int = R.dimen.recommendationBlock_showAll_textSize

    internal var headerTextColor: Int
    internal var headerTextSize: Float
    internal var showAllTextColor: Int
    internal var showAllTextSize: Float

    internal var cardProductViewSettings: CardProductViewSettings

    init {
        headerTextColor = attrs.getColor(R.styleable.RecommendationBlockView_header_textColor, defaultHeaderTextColor)
        headerTextSize = ViewAttrsUtils.getTextSize(resources, attrs, R.styleable.RecommendationBlockView_header_textSize, defaultHeaderTextSize)

        showAllTextColor = attrs.getColor(R.styleable.RecommendationBlockView_showAll_textColor, defaultShowAllTextColor)
        showAllTextSize = ViewAttrsUtils.getTextSize(resources, attrs, R.styleable.RecommendationBlockView_showAll_textSize, defaultShowAllTextSize)

        cardProductViewSettings = CardProductViewSettings(attrs)
    }
}
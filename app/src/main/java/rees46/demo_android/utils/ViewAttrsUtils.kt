package rees46.demo_android.utils

import android.content.res.Resources
import android.content.res.TypedArray
import androidx.annotation.DimenRes

internal object ViewAttrsUtils {

    internal fun getTextSize(resources: Resources, attrs: TypedArray, index: Int, @DimenRes defaultDimenId: Int): Float {
        return pxToSp(resources, attrs.getDimension(index, resources.getDimension(defaultDimenId)))
    }

    internal fun pxToSp(resources: Resources, px: Float) : Float {
        return px / resources.displayMetrics.density
    }
}
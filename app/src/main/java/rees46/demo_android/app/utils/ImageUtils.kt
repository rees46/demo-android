package rees46.demo_android.app.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import rees46.demo_android.R

internal object ImageUtils {

    internal fun updateImage(rootView: View, imageView: ImageView, imageUrl: String) {
        Glide.with(rootView)
            .load(imageUrl)
            .centerCrop()
            .into(imageView)
    }
}

package rees46.demo_android.core.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {

    fun updateImage(rootView: View, imageView: ImageView, imageUrl: String) {
        Glide.with(rootView)
            .load(imageUrl)
            .centerCrop()
            .into(imageView)
    }
}

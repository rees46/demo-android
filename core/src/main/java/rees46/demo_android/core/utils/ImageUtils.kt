package rees46.demo_android.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.updateImage(imageUrl: String) {
    Glide.with(rootView)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

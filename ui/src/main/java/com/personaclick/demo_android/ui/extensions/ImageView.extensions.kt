package com.personaclick.demo_android.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.updateImage(imageUrl: String) {
    Glide.with(rootView)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

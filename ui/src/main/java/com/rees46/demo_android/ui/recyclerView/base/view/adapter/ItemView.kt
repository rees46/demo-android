package com.rees46.demo_android.ui.recyclerView.base.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.rees46.demo_android.ui.recyclerView.base.models.Item

@SuppressLint("ViewConstructor")
abstract class ItemView(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    abstract fun setup()

    abstract fun bind(item: Item, listener: OnItemClickListener)
}

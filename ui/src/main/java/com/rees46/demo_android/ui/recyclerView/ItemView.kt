package com.rees46.demo_android.ui.recyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

@SuppressLint("ViewConstructor")
abstract class ItemView(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    abstract fun bind(item: Item, listener: ItemAdapter.OnClickListener)
}

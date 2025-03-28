package com.personaclick.demo_android.ui.recyclerView.base.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem

@SuppressLint("ViewConstructor")
abstract class RecyclerItemView(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    open fun setup() { }

    abstract fun bind(item: RecyclerViewItem, listener: OnItemClickListener)
}

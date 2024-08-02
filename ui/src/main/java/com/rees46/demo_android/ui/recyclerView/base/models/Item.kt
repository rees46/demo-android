package com.rees46.demo_android.ui.recyclerView.base.models

open class Item {

    open fun areContentsTheSame(oldValue: Item): Boolean =
        this == oldValue
}

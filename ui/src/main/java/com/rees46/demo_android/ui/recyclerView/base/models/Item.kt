package com.rees46.demo_android.ui.recyclerView.base.models

abstract class Item {

    abstract fun areItemsTheSame(oldValue: Item): Boolean
    abstract fun areContentsTheSame(newItem: Item): Boolean
}

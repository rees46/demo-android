package com.rees46.demo_android.ui.recyclerView.base.models

abstract class Item {

    abstract fun areItemsTheSame(anotherItem: Item): Boolean
    abstract fun areContentsTheSame(anotherItem: Item): Boolean
}

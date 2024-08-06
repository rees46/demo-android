package com.rees46.demo_android.ui.recyclerView.base.models

abstract class RecyclerViewItem {

    abstract fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean
    abstract fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean
}

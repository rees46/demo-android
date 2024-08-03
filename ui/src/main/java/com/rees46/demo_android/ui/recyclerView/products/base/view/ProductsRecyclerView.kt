package com.rees46.demo_android.ui.recyclerView.products.base.view

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import com.rees46.demo_android.ui.recyclerView.base.view.RecyclerView
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import com.rees46.demo_android.ui.recyclerView.products.base.view.adapter.ProductItemView

abstract class ProductsRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView<ProductItem, ProductItemView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    val productItems: MutableList<ProductItem> = ArrayList()

    fun addProductItems(productItems: Collection<ProductItem>) {
        this.productItems.clear()
        this.productItems.addAll(productItems)

        Handler(context.mainLooper).post {
            adapter?.notifyDataSetChanged()
        }
    }
}

package com.rees46.demo_android.ui.recyclerView.products.recommendationBlock.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.base.view.ProductsRecyclerView
import com.rees46.demo_android.ui.recyclerView.products.recommendationBlock.view.adapter.RecommendationProductsAdapter

class RecommendationProductsRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ProductsRecyclerView(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    override fun createAdapter(listener: OnItemClickListener): RecommendationProductsAdapter =
        RecommendationProductsAdapter(
            context = context,
            productItems = productItems,
            listener = listener
        )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = HORIZONTAL
            }
}

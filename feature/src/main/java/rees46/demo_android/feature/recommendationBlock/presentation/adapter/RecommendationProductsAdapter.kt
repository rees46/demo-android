package rees46.demo_android.feature.recommendationBlock.presentation.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.RecyclerItemView
import com.rees46.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.adapter.ProductsAdapter
import com.rees46.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import rees46.demo_android.feature.recommendationBlock.presentation.view.recyclerView.RecommendationProductItemView

class RecommendationProductsAdapter(
    private val context: Context,
    productItems: List<ProductRecyclerViewItem>,
    listener: OnItemClickListener
) : ProductsAdapter(
    items = productItems,
    listener = listener
) {

    override fun createItemView(): RecyclerItemView =
        RecommendationProductItemView(
            context = context
        )
}

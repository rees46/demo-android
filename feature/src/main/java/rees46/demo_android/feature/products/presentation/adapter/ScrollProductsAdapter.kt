package rees46.demo_android.feature.products.presentation.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.products.adapter.ProductsAdapter
import com.rees46.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import rees46.demo_android.feature.products.presentation.view.recyclerView.ScrollProductItemView

class ScrollProductsAdapter(
    private val context: Context,
    productItems: List<ProductRecyclerViewItem>,
    listener: OnItemClickListener
) : ProductsAdapter(
    items = productItems,
    listener = listener
) {

    override fun createItemView(): ItemView =
        ScrollProductItemView(
            context = context
        )
}

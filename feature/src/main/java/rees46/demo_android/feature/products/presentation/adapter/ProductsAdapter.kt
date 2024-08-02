package rees46.demo_android.feature.products.presentation.adapter

import android.content.Context
import com.rees46.demo_android.ui.recyclerView.ItemAdapter
import com.rees46.demo_android.ui.recyclerView.ItemView

class ProductsAdapter(
    private val context: Context,
    productItems: List<ProductItem>,
    private val productViewSettings: ProductViewSettings,
    listener: OnClickListener
) : ItemAdapter<ProductView>(
    context = context,
    items = productItems,
    listener = listener
) {

    override fun createItemView(): ItemView =
        ProductView(
            context = context,
            productViewSettings = productViewSettings,
            attrs = null
        )
}

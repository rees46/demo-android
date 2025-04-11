package personaclick.demo_android.feature.products.presentation.adapter

import android.content.Context
import com.personaclick.demo_android.ui.recyclerView.base.view.RecyclerItemView
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.personaclick.demo_android.ui.recyclerView.products.adapter.ProductsAdapter
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import personaclick.demo_android.feature.products.presentation.view.recyclerView.ScrollProductItemView

class ScrollProductsAdapter(
    private val context: Context,
    productItems: List<ProductRecyclerViewItem>,
    listener: OnItemClickListener
) : ProductsAdapter(
    items = productItems,
    listener = listener
) {

    override fun createItemView(): RecyclerItemView =
        ScrollProductItemView(
            context = context
        )
}

package personaclick.demo_android.feature.cart.presentation.adapter

import android.content.Context
import com.personaclick.demo_android.ui.recyclerView.base.adapter.ListItemAdapter
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import personaclick.demo_android.feature.cart.presentation.models.CartProductRecyclerViewItem
import personaclick.demo_android.feature.cart.presentation.view.recyclerView.CartProductItemView

class CartProductsAdapter(
    private val context: Context,
    cartProductItems: List<CartProductRecyclerViewItem>,
    listener: OnItemClickListener
) : ListItemAdapter<CartProductRecyclerViewItem, CartProductItemView>(
    items = cartProductItems,
    listener = listener
) {

    override fun createItemView(): CartProductItemView =
        CartProductItemView(
            context = context
        )
}

package rees46.demo_android.feature.cart.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.rees46.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ListItemAdapter
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import rees46.demo_android.feature.cart.presentation.models.CartProductRecyclerViewItem

class CartProductsRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListRecyclerView<CartProductRecyclerViewItem, CartProductItemView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    override fun createAdapter(
        listener: OnItemClickListener
    ): ListItemAdapter<CartProductRecyclerViewItem, CartProductItemView> =
        rees46.demo_android.feature.cart.presentation.adapter.CartProductsAdapter(
            context = context,
            cartProductItems = items,
            listener = listener
        )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = VERTICAL
            }
}

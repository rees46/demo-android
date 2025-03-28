package personaclick.demo_android.feature.cart.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.personaclick.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.personaclick.demo_android.ui.recyclerView.base.adapter.ListItemAdapter
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import personaclick.demo_android.feature.cart.presentation.models.CartProductRecyclerViewItem

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
        personaclick.demo_android.feature.cart.presentation.adapter.CartProductsAdapter(
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

package personaclick.demo_android.feature.products.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.personaclick.demo_android.ui.recyclerView.products.view.ProductsRecyclerView
import personaclick.demo_android.feature.products.presentation.adapter.ScrollProductsAdapter

class ScrollProductsRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ProductsRecyclerView(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    override fun createAdapter(listener: OnItemClickListener): ScrollProductsAdapter =
        ScrollProductsAdapter(
            context = context,
            productItems = items,
            listener = listener
        )

    override fun createLayoutManager(): LayoutManager =
        GridLayoutManager(context, GRID_LAYOUT_COUNT)

    companion object {
        private const val GRID_LAYOUT_COUNT = 2
    }
}

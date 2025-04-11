package personaclick.demo_android.feature.search.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.personaclick.demo_android.ui.recyclerView.base.view.ListRecyclerView
import com.personaclick.demo_android.ui.recyclerView.base.adapter.ListItemAdapter
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import personaclick.demo_android.feature.search.presentation.adapter.SearchProductAdapter

class SearchProductsRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ListRecyclerView<ProductRecyclerViewItem, SearchProductItemView>(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    override fun createAdapter(
        listener: OnItemClickListener
    ): ListItemAdapter<ProductRecyclerViewItem, SearchProductItemView> =
        SearchProductAdapter(
           context = context,
           items = items,
           listener = listener
       )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = VERTICAL
            }
}

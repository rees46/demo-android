package personaclick.demo_android.feature.recommendationBlock.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.personaclick.demo_android.ui.recyclerView.products.view.ProductsRecyclerView

class RecommendationProductsRecyclerView @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ProductsRecyclerView(
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr
) {

    override fun createAdapter(listener: OnItemClickListener): personaclick.demo_android.feature.recommendationBlock.presentation.adapter.RecommendationProductsAdapter =
        personaclick.demo_android.feature.recommendationBlock.presentation.adapter.RecommendationProductsAdapter(
            context = context,
            productItems = items,
            listener = listener
        )

    override fun createLayoutManager(): LayoutManager =
        LinearLayoutManager(context)
            .apply {
                orientation = HORIZONTAL
            }
}

package personaclick.demo_android.feature.search.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.personaclick.demo_android.ui.recyclerView.base.view.RecyclerItemView
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import personaclick.demo_android.databinding.ViewSearchCategoryItemBinding
import personaclick.demo_android.feature.search.presentation.models.CategoryRecyclerViewItem

class SearchCategoryItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerItemView(
    context = context,
    attrs = attrs
) {
    private var binding: ViewSearchCategoryItemBinding =
        ViewSearchCategoryItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun bind(item: RecyclerViewItem, listener: OnItemClickListener) {
        with(binding) {
            with(item as CategoryRecyclerViewItem) {
                categoryName.text = name

                rootView.setOnClickListener{
                    listener.onItemClick(item)
                }
            }
        }
    }
}

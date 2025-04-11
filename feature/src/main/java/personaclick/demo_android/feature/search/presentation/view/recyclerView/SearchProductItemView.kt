package personaclick.demo_android.feature.search.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.personaclick.demo_android.ui.extensions.updateImage
import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.personaclick.demo_android.ui.recyclerView.base.view.RecyclerItemView
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import personaclick.demo_android.databinding.ViewSearchProductItemBinding

class SearchProductItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : RecyclerItemView(
    context = context,
    attrs = attrs
) {

    private var binding: ViewSearchProductItemBinding =
        ViewSearchProductItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun bind(item: RecyclerViewItem, listener: OnItemClickListener) {
        with(binding) {
            with(item as ProductRecyclerViewItem) {
                productNameText.text = name
                priceText.text = priceFormatted
                productImageView.updateImage(pictureUrl)

                rootView.setOnClickListener{
                    listener.onItemClick(item)
                }
            }
        }
    }
}

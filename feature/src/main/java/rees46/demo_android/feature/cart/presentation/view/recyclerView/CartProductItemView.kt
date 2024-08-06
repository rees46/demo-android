package rees46.demo_android.feature.cart.presentation.view.recyclerView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.rees46.demo_android.ui.extensions.updateImage
import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import rees46.demo_android.databinding.ViewCartProductItemBinding
import rees46.demo_android.feature.cart.presentation.models.CartProductRecyclerViewItem

class CartProductItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ItemView(
    context = context,
    attrs = attrs
) {

    private var binding: ViewCartProductItemBinding =
        ViewCartProductItemBinding.inflate(LayoutInflater.from(context), this, true)

    override fun bind(item: RecyclerViewItem, listener: OnItemClickListener) {
        with(binding) {
            removeButton.setOnClickListener {
                listener.onItemClick(item)
            }

            val cartProductItem = item as CartProductRecyclerViewItem

            with(cartProductItem.productItem) {
                productImageView.updateImage(pictureUrl)

                productNameText.text = name
                producerNameText.text = producerName
                priceText.text = (price?.times(cartProductItem.quantity)).toString()
                productQuantity.text = "x${cartProductItem.quantity}"
            }
        }
    }
}

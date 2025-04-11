package personaclick.demo_android.feature.cart.presentation.models

import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem

data class CartProductRecyclerViewItem(
    val productItem: ProductRecyclerViewItem,
    var quantity: Int
) : RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val cartProductItem = anotherItem as CartProductRecyclerViewItem

        return productItem.id == cartProductItem.productItem.id
    }

    override fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean =
        this == anotherItem
}

package rees46.demo_android.feature.cart.presentation.models

import com.rees46.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem

data class CartProductRecyclerViewItem(
    val productItem: ProductItem,
    var quantity: Int
) : RecyclerViewItem() {

    override fun areItemsTheSame(anotherItem: RecyclerViewItem): Boolean {
        val cartProductItem = anotherItem as CartProductRecyclerViewItem

        return productItem.id == cartProductItem.productItem.id
    }

    override fun areContentsTheSame(anotherItem: RecyclerViewItem): Boolean =
        this == anotherItem
}

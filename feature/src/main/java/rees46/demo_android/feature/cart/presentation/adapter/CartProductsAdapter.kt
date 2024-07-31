package rees46.demo_android.feature.cart.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.feature.cart.domain.models.CartProduct

class CartProductsAdapter(
    private val context: Context,
    private val onClickRemoveCart: (CartProduct) -> Unit
) : ListAdapter<CartProduct, CartProductsAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(CartProductDiffCallback()).build()
) {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val cartProductView = CartProductView(
            context = context,
            attrs = null
        )
        return ViewHolder(cartProductView)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        getItem(position).run(viewHolder::bind)
    }

    override fun submitList(list: MutableList<CartProduct>?) {
        super.submitList(list)
    }

    inner class ViewHolder(private val view: CartProductView) : RecyclerView.ViewHolder(view) {

        fun bind(cartProduct: CartProduct) {
            view.run {
                updateCartProduct(cartProduct)
                setupOnRemoveListener(onClickRemoveCart::invoke)
            }
        }
    }

    private class CartProductDiffCallback : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(
            oldItem: CartProduct,
            newItem: CartProduct
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CartProduct,
            newItem: CartProduct
        ): Boolean = oldItem.product.id == newItem.product.id
    }
}

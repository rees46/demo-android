package rees46.demo_android.feature.cart.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.feature.productDetails.domain.models.CartProductDto

class CartProductsAdapter(
    private val context: Context,
    private val onClickRemoveCart: (CartProductDto) -> Unit
) : ListAdapter<CartProductDto, CartProductsAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(CartProductDiffCallback()).build()
) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val cartProductView = CartProductView(context, null)
        return ViewHolder(cartProductView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getItem(position).run(viewHolder::bind)
    }

    override fun submitList(list: MutableList<CartProductDto>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: CartProductView) : RecyclerView.ViewHolder(view) {

        fun bind(cartProduct: CartProductDto) {
            view.run {
                updateCartProduct(cartProduct)
                setupOnRemoveListener(onClickRemoveCart::invoke)
            }
        }
    }

    private class CartProductDiffCallback : DiffUtil.ItemCallback<CartProductDto>() {
        override fun areItemsTheSame(
            oldItem: CartProductDto,
            newItem: CartProductDto
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CartProductDto,
            newItem: CartProductDto
        ): Boolean = oldItem.product.id == newItem.product.id
    }
}
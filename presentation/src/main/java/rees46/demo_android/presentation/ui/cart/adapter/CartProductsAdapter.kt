package rees46.demo_android.presentation.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.databinding.CartProductItemBinding
import rees46.demo_android.domain.entities.CartProductDto

class CartProductsAdapter(
    private val onClickRemoveCart: (CartProductDto) -> Unit
) : ListAdapter<CartProductDto, CartProductsAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(CartProductDiffCallback()).build()
) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val cartProductView = CartProductItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(cartProductView.root)
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
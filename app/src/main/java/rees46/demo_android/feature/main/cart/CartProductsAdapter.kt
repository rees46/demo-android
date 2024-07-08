package rees46.demo_android.feature.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.databinding.CartProductItemBinding
import rees46.demo_android.entities.products.CartProductEntity

class CartProductsAdapter(
    private val onClickRemoveCart: (CartProductEntity) -> Unit
) : ListAdapter<CartProductEntity, CartProductsAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(CartProductDiffCallback()).build()
) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val cartProductView =
            CartProductItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(cartProductView.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getItem(position).run(viewHolder::bind)
    }

    override fun submitList(list: MutableList<CartProductEntity>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: CartProductView) : RecyclerView.ViewHolder(view) {

        fun bind(cartProduct: CartProductEntity) {
            view.run {
                updateCartProduct(cartProduct)
                setupOnRemoveListener(onClickRemoveCart::invoke)
            }
        }
    }

    private class CartProductDiffCallback : DiffUtil.ItemCallback<CartProductEntity>() {
        override fun areItemsTheSame(
            oldItem: CartProductEntity,
            newItem: CartProductEntity
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CartProductEntity,
            newItem: CartProductEntity
        ): Boolean = oldItem.product.id == newItem.product.id
    }
}
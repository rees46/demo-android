package rees46.demo_android.features.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.databinding.CartProductItemBinding
import rees46.demo_android.entity.productsEntity.CartProductEntity

class CartProductsAdapter(
    private val onClickRemoveCart: (CartProductEntity) -> Unit
) : ListAdapter<CartProductEntity, CartProductsAdapter.ViewHolder>(CartProductDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val cartProductView =
            CartProductItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(cartProductView.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getItem(position).run(viewHolder::bind)
    }

    inner class ViewHolder(private val view: CartProductView) : RecyclerView.ViewHolder(view) {

        fun bind(cartProduct: CartProductEntity) {
            view.run {
                updateCartProduct(cartProduct)
                setOnClickListener { onClickRemoveCart.invoke(cartProduct) }
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
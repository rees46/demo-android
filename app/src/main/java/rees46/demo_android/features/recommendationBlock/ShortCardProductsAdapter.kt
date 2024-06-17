package rees46.demo_android.features.recommendationBlock

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R

class ShortCardProductsAdapter(
    private val context: Context,
    private val shortCardProducts: List<ShortCardProduct>,
    private val listener: ClickListener
) : RecyclerView.Adapter<ShortCardProductsAdapter.ViewHolder>() {

    interface ClickListener {
        fun onCardProductClick(productId: Int)
    }

    inner class ViewHolder(view: View, private val listener: ClickListener)
        : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var productId: Int = 0

        fun bind(cardProduct: ShortCardProduct) {
            productId = cardProduct.productId
        }

        override fun onClick(view: View) {
            listener.onCardProductClick(productId)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val shortCardProductView = ShortCardProductView(context, null)

        return ViewHolder(shortCardProductView, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(shortCardProducts[position])
    }

    override fun getItemCount(): Int {
        return shortCardProducts.size
    }
}
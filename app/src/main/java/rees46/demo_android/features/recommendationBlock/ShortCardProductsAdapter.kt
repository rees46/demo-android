package rees46.demo_android.features.recommendationBlock

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.features.product.Product

class ShortCardProductsAdapter(
    private val context: Context,
    private val products: List<Product>,
    private val listener: ClickListener
) : RecyclerView.Adapter<ShortCardProductsAdapter.ViewHolder>() {

    interface ClickListener {
        fun onCardProductClick(productId: Int)
    }

    inner class ViewHolder(private val view: View, private val listener: ClickListener)
        : RecyclerView.ViewHolder(view) {

        private var productId: Int = 0

        fun bind(product: Product) {
            productId = product.productId

            view.setOnClickListener {
                listener.onCardProductClick(productId)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val shortCardProductView = ShortCardProductView(context, null)

        return ViewHolder(shortCardProductView, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
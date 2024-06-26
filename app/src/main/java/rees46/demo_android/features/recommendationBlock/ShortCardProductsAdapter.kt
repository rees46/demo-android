package rees46.demo_android.features.recommendationBlock

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.core_ui.ShortCardProductView
import rees46.demo_android.features.product.Product

class ShortCardProductsAdapter(
    private val context: Context,
    private val products: List<Product>,
    private val cardProductViewSettings: CardProductViewSettings,
    private val listener: ClickListener
) : RecyclerView.Adapter<ShortCardProductsAdapter.ViewHolder>() {

    interface ClickListener {
        fun onCardProductClick(product: Product)
    }

    inner class ViewHolder(private val view: View, private val listener: ClickListener)
        : RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            (view as ShortCardProductView).updateProduct(product)

            view.setOnClickListener {
                listener.onCardProductClick(product)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val shortCardProductView = ShortCardProductView(context, cardProductViewSettings,null)

        return ViewHolder(shortCardProductView, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
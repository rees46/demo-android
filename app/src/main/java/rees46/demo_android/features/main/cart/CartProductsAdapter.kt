package rees46.demo_android.features.main.cart

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.features.product.Product

class CartProductsAdapter(
    private val context: Context,
    private val products: List<Product>,
    private val listener: ClickListener
) : RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    interface ClickListener {
        fun onCartProductClick(productId: String)
    }

    inner class ViewHolder(private val view: View, private val listener: ClickListener)
        : RecyclerView.ViewHolder(view) {

        private var productId: String = ""

        fun bind(product: Product) {
            productId = product.id

            view.setOnClickListener {
                listener.onCartProductClick(productId)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val cartProductView = CartProductView(context, null)

        return ViewHolder(cartProductView, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
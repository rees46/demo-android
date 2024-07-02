package rees46.demo_android.features.main.category

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.features.product.Product

class CardProductsAdapter(
    private val context: Context,
    private val listener: ClickListener
) : RecyclerView.Adapter<CardProductsAdapter.ViewHolder>() {

    private val products: MutableList<Product> = ArrayList()

    interface ClickListener {
        fun onCardProductClick(productId: String)
    }

    inner class ViewHolder(private val view: CardProductView, private val listener: ClickListener)
        : RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            view.updateProduct(product)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val cardProductView = CardProductView(context,null)

        return ViewHolder(cardProductView, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProducts(products: Collection<Product>) {
        this.products.clear()
        addProducts(products)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addProducts(products: Collection<Product>) {
        this.products.addAll(products)

        Handler(context.mainLooper).post {
            notifyDataSetChanged()
        }
    }
}

package rees46.demo_android.features.main.cart

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.features.product.Product

class CartProductsAdapter(
    private val context: Context,
    private val listener: ClickListener
) : RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    private val products: MutableList<Product> = ArrayList()

    interface ClickListener {
        fun onCartProductClick(productId: String)
    }

    inner class ViewHolder(private val view: CartProductView, private val listener: ClickListener)
        : RecyclerView.ViewHolder(view) {

        private var productId: String = ""

        fun bind(product: Product) {
            productId = product.id

            view.updateProduct(product)

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

    fun updateProducts(products: Collection<Product>) {
        this.products.clear()
        addCardProducts(products)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(products: Collection<Product>) {
        this.products.addAll(products)

        Handler(context.mainLooper).post {
            notifyDataSetChanged()
        }
    }
}
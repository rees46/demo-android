package rees46.demo_android.features.main.cart

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CartProductsAdapter(
    private val context: Context,
    private val listener: CartProductView.ClickListener
) : RecyclerView.Adapter<CartProductsAdapter.ViewHolder>() {

    private val cartProducts: MutableList<CartProduct> = ArrayList()

    inner class ViewHolder(private val view: CartProductView)
        : RecyclerView.ViewHolder(view) {

        fun bind(cartProduct: CartProduct) {
            view.updateCartProduct(cartProduct)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val cartProductView = CartProductView(context, null)
        cartProductView.setListener(listener)

        return ViewHolder(cartProductView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(cartProducts[position])
    }

    override fun getItemCount(): Int {
        return cartProducts.size
    }

    fun updateCartProducts(cartProducts: Collection<CartProduct>) {
        this.cartProducts.clear()
        addCartProducts(cartProducts)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCartProducts(cartProducts: Collection<CartProduct>) {
        this.cartProducts.addAll(cartProducts)

        Handler(context.mainLooper).post {
            notifyDataSetChanged()
        }
    }
}
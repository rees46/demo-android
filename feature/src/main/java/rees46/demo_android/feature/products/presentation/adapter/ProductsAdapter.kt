package rees46.demo_android.feature.products.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.feature.productDetails.domain.models.Product

class ProductsAdapter(
    private val context: Context,
    private val products: List<Product>,
    private val productViewSettings: ProductViewSettings,
    private val listener: OnClickListener
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onCardProductClick(product: Product)
    }

    inner class ViewHolder(
        private val productView: ProductView,
        private val listener: OnClickListener
    ) : RecyclerView.ViewHolder(productView) {

        fun bind(product: Product) {
            productView.apply {
                updateProduct(product)

                setOnClickListener {
                    listener.onCardProductClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val productView = ProductView(
            context = context,
            productViewSettings = productViewSettings,
            attrs = null
        )

        return ViewHolder(
            productView = productView,
            listener = listener
        )
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }
}

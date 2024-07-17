package rees46.demo_android.feature.recommendationBlock.presentation.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.feature.product.domain.models.ProductDto

class CardProductsAdapter(
    private val context: Context,
    private val products: List<ProductDto>,
    private val listener: ClickListener
) : RecyclerView.Adapter<CardProductsAdapter.ViewHolder>() {

    interface ClickListener {
        fun onCardProductClick(product: ProductDto)
    }

    inner class ViewHolder(private val view: View, private val listener: ClickListener)
        : RecyclerView.ViewHolder(view) {

        fun bind(product: ProductDto) {
            (view as CardProductView).updateProduct(product)

            view.setOnClickListener {
                listener.onCardProductClick(product)
            }
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
}
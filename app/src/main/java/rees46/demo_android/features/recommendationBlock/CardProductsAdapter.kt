package rees46.demo_android.features.recommendationBlock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R

class CardProductsAdapter(
    private val cardProducts: List<CardProduct>,
    private val listener: ClickListener
) : RecyclerView.Adapter<CardProductsAdapter.ViewHolder>() {

    interface ClickListener {
        fun onCardProductClick(productId: Int)
    }

    inner class ViewHolder(view: View, private val listener: ClickListener)
        : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var productId: Int = 0

        fun bind(cardProduct: CardProduct) {
            productId = cardProduct.productId
        }

        override fun onClick(view: View) {
            listener.onCardProductClick(productId)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.view_card_product, viewGroup, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(cardProducts[position])
    }

    override fun getItemCount(): Int {
        return cardProducts.size
    }
}
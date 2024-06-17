package rees46.demo_android.features.recommendationBlock

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R

class RecommendationBlockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), CardProductsAdapter.ClickListener {

    private lateinit var cardProductsRecyclerView: RecyclerView
    private lateinit var cardProductsAdapter: CardProductsAdapter

    private val cardProducts: MutableList<CardProduct> = ArrayList()

    init {
        inflate(context, R.layout.view_recommendation_block, this)

        initViews()
        setupViews()
    }

    private fun initViews() {
        cardProductsRecyclerView = findViewById(R.id.card_products_recycler_view)
    }

    private fun setupViews() {
        cardProductsAdapter = CardProductsAdapter(cardProducts, this)
        cardProductsRecyclerView.adapter = cardProductsAdapter
    }

    fun updateCardProducts(cardProducts: Collection<CardProduct>) {
        this.cardProducts.clear()
        addCardProducts(cardProducts)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(cardProducts: Collection<CardProduct>) {
        this.cardProducts.addAll(cardProducts)

        Handler(context.mainLooper).post {
            cardProductsAdapter.notifyDataSetChanged()
        }
    }

    override fun onCardProductClick(productId: Int) {
        TODO("Not yet implemented")
    }
}
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
) : ConstraintLayout(context, attrs), ShortCardProductsAdapter.ClickListener {

    private lateinit var shortCardProductsRecyclerView: RecyclerView
    private lateinit var shortCardProductsAdapter: ShortCardProductsAdapter

    private val cardProducts: MutableList<ShortCardProduct> = ArrayList()

    init {
        inflate(context, R.layout.view_recommendation_block, this)

        initViews()
        setupViews()
    }

    private fun initViews() {
        shortCardProductsRecyclerView = findViewById(R.id.card_products_recycler_view)
    }

    private fun setupViews() {
        shortCardProductsAdapter = ShortCardProductsAdapter(context, cardProducts, this)
        shortCardProductsRecyclerView.adapter = shortCardProductsAdapter
    }

    fun updateCardProducts(cardProducts: Collection<ShortCardProduct>) {
        this.cardProducts.clear()
        addCardProducts(cardProducts)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(cardProducts: Collection<ShortCardProduct>) {
        this.cardProducts.addAll(cardProducts)

        Handler(context.mainLooper).post {
            shortCardProductsAdapter.notifyDataSetChanged()
        }
    }

    override fun onCardProductClick(productId: Int) {
        TODO("Not yet implemented")
    }
}
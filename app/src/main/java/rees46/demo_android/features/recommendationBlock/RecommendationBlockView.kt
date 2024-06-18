package rees46.demo_android.features.recommendationBlock

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R
import rees46.demo_android.features.product.Product

class RecommendationBlockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), ShortCardProductsAdapter.ClickListener {

    interface ClickListener {
        fun onCardProductClick(productId: Int)
    }

    private lateinit var shortCardProductsRecyclerView: RecyclerView
    private lateinit var shortCardProductsAdapter: ShortCardProductsAdapter

    private val products: MutableList<Product> = ArrayList()
    private var listener: ClickListener? = null

    init {
        inflate(context, R.layout.view_recommendation_block, this)

        initViews()
        setupViews()
    }

    private fun initViews() {
        shortCardProductsRecyclerView = findViewById(R.id.card_products_recycler_view)
    }

    private fun setupViews() {
        shortCardProductsAdapter = ShortCardProductsAdapter(context, products, this)
        shortCardProductsRecyclerView.adapter = shortCardProductsAdapter
    }

    fun updateProducts(products: Collection<Product>) {
        this.products.clear()
        addCardProducts(products)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(products: Collection<Product>) {
        this.products.addAll(products)

        Handler(context.mainLooper).post {
            shortCardProductsAdapter.notifyDataSetChanged()
        }
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCardProductClick(productId: Int) {
        listener?.onCardProductClick(productId)
    }
}
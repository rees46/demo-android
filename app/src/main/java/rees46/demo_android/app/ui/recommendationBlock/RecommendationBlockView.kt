package rees46.demo_android.app.ui.recommendationBlock

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R
import rees46.demo_android.domain.entities.ProductEntity
import rees46.demo_android.app.ui.recommendationBlock.adapter.CardProductsAdapter
import rees46.demo_android.domain.entities.RecommendationEntity

class RecommendationBlockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), CardProductsAdapter.ClickListener {

    interface ClickListener {
        fun onCardProductClick(product: ProductEntity)
    }

    var onCardProductClick: (ProductEntity) -> Unit = {  }
    var onShowAllClick: (List<ProductEntity>) -> Unit = {  }

    private lateinit var headerTextView: TextView
    private lateinit var showAllTextView: TextView
    private lateinit var cardProductsRecyclerView: RecyclerView
    private lateinit var cardProductsAdapter: CardProductsAdapter

    private val products: MutableList<ProductEntity> = ArrayList()
    private var listener: ClickListener? = null

    init {
        inflate(context, R.layout.view_recommendation_block, this)

        initViews()

        setupViews()
    }

    private fun initViews() {
        headerTextView = findViewById(R.id.header_text)
        showAllTextView = findViewById(R.id.show_all_text)
        cardProductsRecyclerView = findViewById(R.id.card_products_recycler_view)
    }

    private fun setupViews() {
        cardProductsAdapter = CardProductsAdapter(context, products, this)
        cardProductsRecyclerView.adapter = cardProductsAdapter

        showAllTextView.setOnClickListener { onShowAllClick.invoke(products) }
    }

    fun update(recommendation: RecommendationEntity) {
        this.products.clear()
        addCardProducts(recommendation.products)

        setHeaderText(recommendation.title)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(products: Collection<ProductEntity>) {
        this.products.addAll(products)

        Handler(context.mainLooper).post {
            cardProductsAdapter.notifyDataSetChanged()
        }
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCardProductClick(product: ProductEntity) {
        onCardProductClick.invoke(product)
    }

    private fun setHeaderText(text: String) {
        headerTextView.text = text
    }
}
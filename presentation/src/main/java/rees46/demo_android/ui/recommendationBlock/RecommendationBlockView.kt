package rees46.demo_android.ui.recommendationBlock

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R
import rees46.demo_android.data.products.ProductDto
import rees46.demo_android.ui.recommendationBlock.adapter.CardProductsAdapter

class RecommendationBlockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), CardProductsAdapter.ClickListener {

    interface ClickListener {
        fun onCardProductClick(product: ProductDto)
    }

    var onCardProductClick: (ProductDto) -> Unit = {  }

    private lateinit var headerTextView: TextView
    private lateinit var showAllTextView: TextView
    private lateinit var cardProductsRecyclerView: RecyclerView
    private lateinit var cardProductsAdapter: CardProductsAdapter

    private val products: MutableList<ProductDto> = ArrayList()
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
    }

    fun updateProducts(products: Collection<ProductDto>) {
        this.products.clear()
        addCardProducts(products)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(products: Collection<ProductDto>) {
        this.products.addAll(products)

        Handler(context.mainLooper).post {
            cardProductsAdapter.notifyDataSetChanged()
        }
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCardProductClick(product: ProductDto) {
        onCardProductClick.invoke(product)
    }

    fun setHeaderText(text: String) {
        headerTextView.text = text
    }
}

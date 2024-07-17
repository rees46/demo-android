package rees46.demo_android.app.ui.recommendationBlock

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R
import rees46.demo_android.domain.models.ProductDto
import rees46.demo_android.app.ui.recommendationBlock.adapter.CardProductsAdapter
import rees46.demo_android.domain.models.RecommendationDto

class RecommendationBlockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), CardProductsAdapter.ClickListener {

    interface ClickListener {
        fun onCardProductClick(product: ProductDto)
    }

    var onCardProductClick: (ProductDto) -> Unit = {  }
    var onShowAllClick: (List<ProductDto>) -> Unit = {  }

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

        changeView(false)
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

    fun update(recommendation: RecommendationDto) {
        this.products.clear()
        addCardProducts(recommendation.products)

        setHeaderText(recommendation.title)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(products: Collection<ProductDto>) {
        this.products.addAll(products)

        Handler(context.mainLooper).post {
            cardProductsAdapter.notifyDataSetChanged()
        }

        changeView(true)
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCardProductClick(product: ProductDto) {
        onCardProductClick.invoke(product)
    }

    private fun setHeaderText(text: String) {
        headerTextView.text = text
    }

    private fun changeView(show: Boolean) {
        val visibility = if (show) VISIBLE else GONE

        headerTextView.visibility = visibility
        showAllTextView.visibility = visibility
        cardProductsRecyclerView.visibility = visibility
    }
}

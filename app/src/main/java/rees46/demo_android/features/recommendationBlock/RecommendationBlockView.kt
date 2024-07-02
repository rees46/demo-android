package rees46.demo_android.features.recommendationBlock

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R
import rees46.demo_android.features.product.Product

class RecommendationBlockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), CardProductsAdapter.ClickListener {

    interface ClickListener {
        fun onCardProductClick(product: Product)
    }

    private lateinit var headerTextView: TextView
    private lateinit var showAllTextView: TextView
    private lateinit var cardProductsRecyclerView: RecyclerView
    private lateinit var cardProductsAdapter: CardProductsAdapter

    private val products: MutableList<Product> = ArrayList()
    private var listener: ClickListener? = null

    private lateinit var recommendationBlockViewSettings: RecommendationBlockViewSettings

    init {
        inflate(context, R.layout.view_recommendation_block, this)

        initViews()

        applyAttr(attrs)

        setupViews()
    }

    private fun initViews() {
        headerTextView = findViewById(R.id.header_text)
        showAllTextView = findViewById(R.id.show_all_text)
        cardProductsRecyclerView = findViewById(R.id.card_products_recycler_view)
    }

    private fun setupViews() {
        cardProductsAdapter = CardProductsAdapter(context, products,
            recommendationBlockViewSettings.cardProductViewSettings, this)
        cardProductsRecyclerView.adapter = cardProductsAdapter

        recommendationBlockViewSettings.apply {
            headerTextView.setTextColor(headerTextColor)
            headerTextView.textSize = headerTextSize

            showAllTextView.setTextColor(showAllTextColor)
            showAllTextView.textSize = showAllTextSize
        }
    }

    private fun applyAttr(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.RecommendationBlockView,
            0, 0).apply {

            try {
                recommendationBlockViewSettings = RecommendationBlockViewSettings(resources,this)
            } finally {
                recycle()
            }
        }
    }

    fun updateProducts(products: Collection<Product>) {
        this.products.clear()
        addCardProducts(products)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(products: Collection<Product>) {
        this.products.addAll(products)

        Handler(context.mainLooper).post {
            cardProductsAdapter.notifyDataSetChanged()
        }
    }

    fun setClickListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCardProductClick(product: Product) {
        listener?.onCardProductClick(product)
    }

    fun setHeaderText(text: String) {
        headerTextView.text = text
    }

    fun setHeaderTextColor(color: Int) {
        headerTextView.setTextColor(color)

        invalidateView()
    }

    fun setHeaderTextSize(textSize: Float) {
        headerTextView.textSize = textSize

        invalidateView()
    }

    fun setShowAllTextColor(color: Int) {
        showAllTextView.setTextColor(color)

        invalidateView()
    }

    fun setShowAllTextSize(textSize: Float) {
        showAllTextView.textSize = textSize

        invalidateView()
    }

    private fun invalidateView() {
        invalidate()
        requestLayout()
    }
}
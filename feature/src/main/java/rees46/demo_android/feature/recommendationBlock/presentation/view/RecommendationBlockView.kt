package rees46.demo_android.feature.recommendationBlock.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import rees46.demo_android.R
import rees46.demo_android.core.utils.ViewUtils
import rees46.demo_android.feature.productDetails.domain.models.Product
import rees46.demo_android.feature.products.presentation.adapter.ProductViewSettings
import rees46.demo_android.feature.products.presentation.adapter.ProductsAdapter
import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation

class RecommendationBlockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), ProductsAdapter.OnClickListener {

    var onCardProductClick: (Product) -> Unit = {  }
    var onShowAllClick: (List<Product>) -> Unit = {  }

    private lateinit var headerTextView: TextView
    private lateinit var showAllTextView: TextView
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter

    private val products: MutableList<Product> = ArrayList()

    private lateinit var productViewSettings: ProductViewSettings

    init {
        inflate(context, R.layout.view_recommendation_block, this)

        initViews()

        setupProductViewSettings()
        setupViews()

        changeView(false)
    }

    private fun initViews() {
        headerTextView = findViewById(R.id.header_text)
        showAllTextView = findViewById(R.id.show_all_text)
        productsRecyclerView = findViewById(R.id.products_recycler_view)
    }

    private fun setupViews() {
        productsAdapter = ProductsAdapter(
            context = context,
            products = products,
            productViewSettings = productViewSettings,
            listener = this
        )
        productsRecyclerView.adapter = productsAdapter

        showAllTextView.setOnClickListener {
            onShowAllClick.invoke(products)
        }
    }

    fun update(recommendation: Recommendation) {
        this.products.clear()
        addCardProducts(recommendation.products)

        setHeaderText(recommendation.title)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(products: Collection<Product>) {
        this.products.addAll(products)

        Handler(context.mainLooper).post {
            productsAdapter.notifyDataSetChanged()
        }

        changeView(true)
    }

    override fun onCardProductClick(product: Product) {
        onCardProductClick.invoke(product)
    }

    private fun setHeaderText(text: String) {
        headerTextView.text = text
    }

    private fun changeView(show: Boolean) {
        headerTextView.isVisible = show
        showAllTextView.isVisible = show
        productsRecyclerView.isVisible = show
    }

    private fun setupProductViewSettings() {
        productViewSettings = ProductViewSettings(
            width = ViewUtils.convertDpToPixel(
                dp = 140f,
                context = context
            ).toInt(),
            showButton = false
        )
    }
}

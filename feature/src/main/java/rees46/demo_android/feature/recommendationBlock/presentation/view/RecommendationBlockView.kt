package rees46.demo_android.feature.recommendationBlock.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import rees46.demo_android.R
import rees46.demo_android.feature.productDetails.domain.models.Product
import com.rees46.demo_android.ui.recyclerView.products.base.models.ProductItem
import com.rees46.demo_android.ui.recyclerView.products.recommendationBlock.view.RecommendationProductsRecyclerView
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper
import rees46.demo_android.feature.recommendationBlock.domain.models.Recommendation

class RecommendationBlockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), OnItemClickListener {

    private lateinit var headerTextView: TextView
    private lateinit var showAllTextView: TextView
    private lateinit var productsRecyclerView: RecommendationProductsRecyclerView

    private var onCardProductClick: (Product) -> Unit = {  }
    private var onShowAllClick: (List<Product>) -> Unit = {  }

    private lateinit var productItemMapper: ProductItemMapper

    init {
        inflate(context, R.layout.view_recommendation_block, this)

        initViews()

        changeView(false)
    }

    internal fun setup(
        productItemMapper: ProductItemMapper,
        onCardProductClick: (Product) -> Unit = {  },
        onShowAllClick: (List<Product>) -> Unit = {  }
    ) {
        this.productItemMapper = productItemMapper
        this.onCardProductClick = onCardProductClick
        this.onShowAllClick = onShowAllClick

        setupViews()
    }

    private fun initViews() {
        headerTextView = findViewById(R.id.header_text)
        showAllTextView = findViewById(R.id.show_all_text)
        productsRecyclerView = findViewById(R.id.products_recycler_view)
    }

    private fun setupViews() {
        productsRecyclerView.setup(this)

        showAllTextView.setOnClickListener {
            onShowAllClick.invoke(productItemMapper.toProducts(productsRecyclerView.productItems))
        }
    }

    fun update(recommendation: Recommendation) {
        addCardProducts(recommendation.products)

        setHeaderText(recommendation.title)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCardProducts(product: Collection<Product>) {
        Handler(context.mainLooper).post {
            val productItems = productItemMapper.toProductItems(product)
            productsRecyclerView.updateItems(productItems)
        }

        changeView(true)
    }

    private fun setHeaderText(text: String) {
        headerTextView.text = text
    }

    private fun changeView(show: Boolean) {
        headerTextView.isVisible = show
        showAllTextView.isVisible = show
        productsRecyclerView.isVisible = show
    }

    override fun onItemClick(item: Item) {
        val product = productItemMapper.toProduct(item as ProductItem)
        onCardProductClick.invoke(product)
    }
}

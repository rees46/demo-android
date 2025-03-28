package personaclick.demo_android.feature.recommendationBlock.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.personaclick.demo_android.ui.recyclerView.base.models.RecyclerViewItem
import com.personaclick.demo_android.ui.recyclerView.base.listener.OnItemClickListener
import personaclick.demo_android.R
import personaclick.demo_android.feature.productDetails.domain.models.Product
import com.personaclick.demo_android.ui.recyclerView.products.models.ProductRecyclerViewItem
import personaclick.demo_android.feature.recommendationBlock.presentation.view.recyclerView.RecommendationProductsRecyclerView
import personaclick.demo_android.feature.products.presentation.mappers.ProductItemMapper
import personaclick.demo_android.feature.recommendationBlock.domain.models.Recommendation

class RecommendationBlockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs), OnItemClickListener {

    private lateinit var headerTextView: TextView
    private lateinit var showAllTextView: TextView
    private lateinit var productsRecyclerView: RecommendationProductsRecyclerView

    private var onCardProductClick: (Product) -> Unit = { }
    private var onShowAllClick: (List<Product>) -> Unit = { }

    private lateinit var productItemMapper: ProductItemMapper

    init {
        inflate(context, R.layout.view_recommendation_block, this)

        initViews()

        changeView(false)
    }

    internal fun setup(
        productItemMapper: ProductItemMapper,
        titleId: Int = R.string.recommender_title,
        onCardProductClick: (Product) -> Unit = { },
        onShowAllClick: (List<Product>) -> Unit = { },
    ) {
        this.productItemMapper = productItemMapper
        this.onCardProductClick = onCardProductClick
        this.onShowAllClick = onShowAllClick

        setupViews()
        setHeaderText(text = context.getString(titleId))
    }

    private fun initViews() {
        headerTextView = findViewById(R.id.header_text)
        showAllTextView = findViewById(R.id.show_all_text)
        productsRecyclerView = findViewById(R.id.products_recycler_view)
    }

    private fun setupViews() {
        productsRecyclerView.setup(this)

        showAllTextView.setOnClickListener {
            onShowAllClick.invoke(productItemMapper.toProducts(productsRecyclerView.items))
        }
    }

    fun update(recommendation: Recommendation) {
        addCardProducts(recommendation.products)
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

    override fun onItemClick(item: RecyclerViewItem) {
        val product = productItemMapper.toProduct(item as ProductRecyclerViewItem)
        onCardProductClick.invoke(product)
    }
}

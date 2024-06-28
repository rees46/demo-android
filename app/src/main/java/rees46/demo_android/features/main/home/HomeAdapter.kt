package rees46.demo_android.features.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.personalizatio.SDK
import rees46.demo_android.core_ui.RecommendationBlockView
import rees46.demo_android.databinding.RecomendationBlockHolderBinding
import rees46.demo_android.databinding.StoriesViewHolderBinding
import rees46.demo_android.features.main.home.HomeFragmentTypeBlock.Companion.TYPE_STORIES
import rees46.demo_android.features.main.home.HomeFragmentTypeBlock.Companion.TYPE_TOP_TRENDS
import rees46.demo_android.features.product.Product

class HomeAdapter(
    private val sdk: SDK,
    private val productData: MutableList<Product>,
    private val clickProduct: RecommendationBlockView.ClickListener,
    private vararg val typeBlock: HomeFragmentTypeBlock
) :
    RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val storiesViewBinding: StoriesViewHolderBinding =
            StoriesViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        val recommendationBlockHolderBinding: RecomendationBlockHolderBinding =
            RecomendationBlockHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return when(viewType) {
            0 -> StoriesViewHolder(storiesViewBinding, sdk)
            1 -> TopTrendsViewHolder(recommendationBlockHolderBinding, productData, clickProduct)
            else -> StoriesViewHolder(storiesViewBinding, sdk)
        }
    }

    override fun getItemCount(): Int = typeBlock.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind()

    override fun getItemViewType(position: Int): Int = typeBlock[position].type

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bind()
    }

    class StoriesViewHolder(
        private val itemBinding: StoriesViewHolderBinding,
        private val sdk: SDK
    ) : BaseViewHolder(itemBinding.root) {

        override fun bind() {
            sdk.storiesManager.initialize(itemBinding.root)
        }
    }

    class TopTrendsViewHolder(
        private val itemBinding: RecomendationBlockHolderBinding,
        private val data: MutableList<Product>,
        private val onClickProduct: RecommendationBlockView.ClickListener
    ): BaseViewHolder(itemBinding.root) {

        override fun bind() {
            itemBinding.root.updateProducts(data)
            itemBinding.root.setClickListener(onClickProduct)
        }
    }
}


sealed interface HomeFragmentTypeBlock {
    val type: Int

    companion object {

        internal const val TYPE_STORIES = 0
        internal const val TYPE_TOP_TRENDS = 1
    }
}

data class StoriesType(override val type: Int = TYPE_STORIES) : HomeFragmentTypeBlock
data class RecommendationType(override val type: Int = TYPE_TOP_TRENDS, val code: String) : HomeFragmentTypeBlock

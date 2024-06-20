package rees46.demo_android.features.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.personalizatio.SDK
import org.koin.android.ext.android.inject
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.core_ui.RecommendationBlockView

class HomeFragment
    : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    private val sdk: SDK by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeRecyclerView.adapter =
            HomeAdapter(sdk, viewModel.products.toMutableList(), getRecommendationProductClickListener(), StoriesType(), TopTrendsType())
    }

    private fun getRecommendationProductClickListener(): RecommendationBlockView.ClickListener {
        return object : RecommendationBlockView.ClickListener {
            override fun onCardProductClick(productId: Int) {
                val product = viewModel.getProduct(productId)
                if (product != null) {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToCardProductFragment(product)
                    findNavController().navigate(action)
                }
            }
        }
    }
}

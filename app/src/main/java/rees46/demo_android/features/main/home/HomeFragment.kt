package rees46.demo_android.features.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentHomeBinding
import rees46.demo_android.core_ui.RecommendationBlockView

class HomeFragment
    : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newArrivalsRecommendationBlockView.run {
            updateProducts(viewModel.products)
            setClickListener(getRecommendationProductClickListener())
        }
    }

    private fun getRecommendationProductClickListener() : RecommendationBlockView.ClickListener {
        return object : RecommendationBlockView.ClickListener {
            override fun onCardProductClick(productId: Int) {
                val product = viewModel.getProduct(productId)
                if(product != null) {
                    val action = HomeFragmentDirections.actionHomeFragmentToCardProductFragment(product)
                    findNavController().navigate(action)
                }
            }
        }
    }
}

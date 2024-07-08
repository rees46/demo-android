package rees46.demo_android.feature.main.category

import androidx.fragment.app.viewModels
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentCategoryBinding
import rees46.demo_android.feature.main.category.presentation.CategoryViewModel

class CategoryFragment
    : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val viewModel: CategoryViewModel by viewModels()
}

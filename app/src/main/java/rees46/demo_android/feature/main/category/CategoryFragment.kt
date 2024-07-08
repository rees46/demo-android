package rees46.demo_android.feature.main.category

import androidx.fragment.app.viewModels
import rees46.demo_android.core.base.BaseFragment
import rees46.demo_android.databinding.FragmentCategoryBinding

class CategoryFragment
    : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val viewModel: rees46.demo_android.domain.feature.main.category.presentation.CategoryViewModel by viewModels()
}

package rees46.demo_android.ui.main.category

import androidx.fragment.app.viewModels
import rees46.demo_android.ui.base.BaseFragment
import rees46.demo_android.databinding.FragmentCategoryBinding

class CategoryFragment
    : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val viewModel: rees46.demo_android.domain.feature.main.category.presentation.CategoryViewModel by viewModels()
}

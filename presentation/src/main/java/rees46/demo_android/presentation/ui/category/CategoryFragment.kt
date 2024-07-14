package rees46.demo_android.presentation.ui.category

import androidx.fragment.app.viewModels
import rees46.demo_android.ui.base.BaseFragment
import rees46.demo_android.databinding.FragmentCategoryBinding

class CategoryFragment
    : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val viewModel: CategoryViewModel by viewModels()
}

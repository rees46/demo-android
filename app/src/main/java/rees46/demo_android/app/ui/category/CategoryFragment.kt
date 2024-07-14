package rees46.demo_android.app.ui.category

import androidx.fragment.app.viewModels
import rees46.demo_android.app.ui.base.BaseFragment
import rees46.demo_android.databinding.FragmentCategoryBinding

class CategoryFragment
    : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val viewModel: CategoryViewModel by viewModels()
}

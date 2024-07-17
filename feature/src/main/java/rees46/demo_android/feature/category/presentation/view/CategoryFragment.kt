package rees46.demo_android.feature.category.presentation.view

import androidx.fragment.app.viewModels
import rees46.demo_android.feature.BaseFragment
import rees46.demo_android.databinding.FragmentCategoryBinding
import rees46.demo_android.feature.category.presentation.viewmodel.CategoryViewModel

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val viewModel: CategoryViewModel by viewModels()
}

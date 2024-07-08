package rees46.demo_android.feature.main.settings

import androidx.fragment.app.viewModels
import rees46.demo_android.core.view.BaseFragment
import rees46.demo_android.databinding.FragmentSettingsBinding
import rees46.demo_android.feature.main.settings.presentation.SettingsViewModel

class SettingsFragment
    : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel: SettingsViewModel by viewModels()
}

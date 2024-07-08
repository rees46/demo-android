package rees46.demo_android.feature.main.settings

import androidx.fragment.app.viewModels
import rees46.demo_android.core.base.BaseFragment
import rees46.demo_android.databinding.FragmentSettingsBinding

class SettingsFragment
    : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel: rees46.demo_android.domain.feature.main.settings.presentation.SettingsViewModel by viewModels()
}

package rees46.demo_android.app.ui.settings

import androidx.fragment.app.viewModels
import rees46.demo_android.app.base.BaseFragment
import rees46.demo_android.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel: SettingsViewModel by viewModels()
}

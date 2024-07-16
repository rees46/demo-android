package rees46.demo_android.app.ui.settings

import android.os.Bundle
import android.view.View
import com.personalizatio.SDK
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rees46.demo_android.app.base.BaseFragment
import rees46.demo_android.app.utils.SdkUtils
import rees46.demo_android.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel: SettingsViewModel by viewModel()

    private val sdk: SDK by inject<SDK>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.uploadButton.setOnClickListener {
            val storeId = binding.storeKeyTextInput.text?.toString()
            SdkUtils.initialize(sdk, requireContext(), storeId)
        }
    }
}

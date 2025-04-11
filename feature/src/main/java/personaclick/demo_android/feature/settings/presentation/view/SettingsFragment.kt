package personaclick.demo_android.feature.settings.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.personalization.SDK
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import personaclick.demo_android.core.utils.SdkUtils
import personaclick.demo_android.databinding.FragmentSettingsBinding
import personaclick.demo_android.feature.settings.presentation.viewmodel.SettingsViewModel

class SettingsFragment : Fragment() {

    private val viewModel: SettingsViewModel by viewModel()

    private lateinit var binding: FragmentSettingsBinding

    private val sdk: SDK by inject<SDK>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        binding.uploadButton.setOnClickListener {
            val storeId = binding.storeKeyTextInput.text?.toString() ?: ""
            SdkUtils.initialize(
                sdk = sdk,
                context = requireContext(),
                shopId = storeId
            )
        }
    }
}

package rees46.demo_android.feature.home.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.personalization.SDK
import org.koin.android.ext.android.inject
import rees46.demo_android.R
import rees46.demo_android.databinding.FragmentNewFeaturesBinding

class NewFeaturesFragment : Fragment() {
    private lateinit var binding: FragmentNewFeaturesBinding

    private val sdk: SDK by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewFeaturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleInAppNotifications()
    }

    private fun handleInAppNotifications() {
        //TODO remove
        with(binding) {

            alertDialogButton.setOnClickListener {
                sdk.inAppNotificationManager.showAlertDialog(
                    fragmentManager = childFragmentManager,
                    title = requireContext().getString(R.string.alert_dialog_title),
                    message = requireContext().getString(R.string.alert_dialog_message),
                    buttonText = requireContext().getString(R.string.alert_dialog_button_accept)
                )
            }

            fullScreenDialogButton.setOnClickListener {
                sdk.inAppNotificationManager.showFullScreenDialog(
                    fragmentManager = childFragmentManager,
                    title = requireContext().getString(R.string.alert_dialog_title),
                    message = requireContext().getString(R.string.alert_dialog_message),
                    imageUrl = "https://ih1.redbubble.net/image.2487413451.3407/fmp,x_small,gloss,study,product,750x1000.jpg",
                    buttonNegativeText = requireContext().getString(R.string.alert_dialog_button_decline),
                    buttonPositiveText = requireContext().getString(R.string.alert_dialog_button_accept),
                    onNegativeClick = {
                        Log.d(requireContext().javaClass.name, ": onNegativeClick")
                    },
                    onPositiveClick = {
                        Log.d(requireContext().javaClass.name, ": onPositiveClick")
                    },
                )
            }

            bottomSheetDialogButton.setOnClickListener {
                sdk.inAppNotificationManager.showBottomSheetDialog(
                    fragmentManager = childFragmentManager,
                    title = requireContext().getString(R.string.alert_dialog_title),
                    message = requireContext().getString(R.string.alert_dialog_message),
                    imageUrl = "https://ih1.redbubble.net/image.2487413451.3407/fmp,x_small,gloss,study,product,750x1000.jpg",
                    buttonNegativeText = requireContext().getString(R.string.alert_dialog_button_decline),
                    buttonPositiveText = requireContext().getString(R.string.alert_dialog_button_accept),
                    onNegativeClick = {
                        Log.d(requireContext().javaClass.name, ": onNegativeClick")
                    },
                    onPositiveClick = {
                        Log.d(requireContext().javaClass.name, ": onPositiveClick")
                    },
                )
            }

            snackBarButton.setOnClickListener {
                sdk.inAppNotificationManager.showSnackBar(
                    view = requireView(),
                    message = requireContext().getString(R.string.alert_dialog_title),
                    buttonNegativeText = requireContext().getString(R.string.alert_dialog_button_decline),
                    buttonPositiveText = requireContext().getString(R.string.alert_dialog_button_accept),
                    onNegativeClick = {
                        Log.d(requireContext().javaClass.name, ": onNegativeClick")
                    },
                    onPositiveClick = {
                        Log.d(requireContext().javaClass.name, ": onPositiveClick")
                    },
                )
            }
        }
    }
}

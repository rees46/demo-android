package rees46.demo_android.feature.home.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.personalization.SDK
import org.koin.android.ext.android.inject
import rees46.demo_android.R
import rees46.demo_android.databinding.FragmentInAppNotificationsBinding

class InAppNotificationsFragment : Fragment() {

    private lateinit var binding: FragmentInAppNotificationsBinding

    private val sdk: SDK by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInAppNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleInAppNotifications()
    }

    private fun handleInAppNotifications() {
        sdk.initializeFragmentManager(fragmentManager = parentFragmentManager)
        with(binding) {
            val debugFullScreenMessage =
                resources.getString(R.string.alert_dialog_full_screen_message)
            val buttonNegative = resources.getString(R.string.alert_dialog_button_decline_title)
            val buttonPositive = resources.getString(R.string.alert_dialog_button_accept_title)
            val debugMessage = resources.getString(R.string.alert_dialog_message)
            val debugTitle = resources.getString(R.string.alert_dialog_title)

            val buttonPositiveColor =
                ContextCompat.getColor(requireContext(), R.color.buttonAcceptColor)
            val buttonNegativeColor = ContextCompat.getColor(requireContext(), R.color.colorGray)

            val debugImageUrl =
                "https://mir-s3-cdn-cf.behance.net/projects/404/01d316151239201.Y3JvcCwzMzA0LDI1ODUsMzQzLDA.png"

            alertDialogButton.setOnClickListener {
                sdk.showAlertDialog(
                    title = debugTitle,
                    message = debugMessage,
                    imageUrl = debugImageUrl,
                    buttonNegativeText = buttonNegative,
                    buttonPositiveText = buttonPositive,
                    buttonNegativeColor = buttonNegativeColor,
                    buttonPositiveColor = buttonPositiveColor,
                    onNegativeClick = {},
                    onPositiveClick = {},
                )
            }

            fullScreenDialogButton.setOnClickListener {
                sdk.showFullScreenDialog(
                    title = debugTitle,
                    message = debugFullScreenMessage,
                    imageUrl = debugImageUrl,
                    buttonNegativeText = buttonNegative,
                    buttonPositiveText = buttonPositive,
                    buttonNegativeColor = buttonNegativeColor,
                    buttonPositiveColor = buttonPositiveColor,
                    onNegativeClick = {
                    },
                    onPositiveClick = {
                    },
                )
            }

            bottomSheetDialogButton.setOnClickListener {
                sdk.showBottomSheetDialog(
                    title = debugTitle,
                    message = debugMessage,
                    imageUrl = debugImageUrl,
                    buttonNegativeText = null,
                    buttonPositiveText = buttonPositive,
                    buttonNegativeColor = buttonNegativeColor,
                    buttonPositiveColor = buttonPositiveColor,
                    onNegativeClick = {
                    },
                    onPositiveClick = {
                    },
                )
            }

            snackBarButton.setOnClickListener {
                showSnackBar(
                    message = debugTitle,
                    buttonNegativeText = buttonPositive,
                    buttonPositiveText = buttonNegative
                )
            }
        }
    }

    private fun showSnackBar(
        message: String,
        buttonNegativeText: String,
        buttonPositiveText: String
    ) {
        sdk.inAppNotificationManager.showSnackBar(
            view = requireView(),
            message = message,
            buttonNegativeText = buttonNegativeText,
            buttonPositiveText = buttonPositiveText,
            onNegativeClick = { logClick("onNegativeClick") },
            onPositiveClick = { logClick("onPositiveClick") }
        )
    }

    private fun logClick(action: String) {
        Log.d(InAppNotificationsFragment::class.java.simpleName, action)
    }
}

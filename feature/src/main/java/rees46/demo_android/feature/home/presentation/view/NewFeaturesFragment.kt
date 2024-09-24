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
        with(binding) {

            val title = getString(R.string.alert_dialog_title)
            val message = getString(R.string.alert_dialog_message)
            val buttonAccept = getString(R.string.alert_dialog_button_accept)
            val buttonDecline = getString(R.string.alert_dialog_button_decline)
            val imageUrl =
                "https://ih1.redbubble.net/image.2487413451.3407/fmp,x_small,gloss,study,product,750x1000.jpg"

            alertDialogButton.setOnClickListener {
                showAlertDialog(title, message, buttonAccept)
            }

            fullScreenDialogButton.setOnClickListener {
                showFullScreenDialog(
                    title = title,
                    message = message,
                    imageUrl = imageUrl,
                    buttonNegativeText = buttonDecline,
                    buttonPositiveText = buttonAccept
                )
            }

            bottomSheetDialogButton.setOnClickListener {
                showBottomSheetDialog(
                    title = title,
                    message = message,
                    imageUrl = imageUrl,
                    buttonNegativeText = buttonDecline,
                    buttonPositiveText = buttonAccept
                )
            }

            snackBarButton.setOnClickListener {
                showSnackBar(
                    message = title,
                    buttonNegativeText = buttonDecline,
                    buttonPositiveText = buttonAccept
                )
            }
        }
    }

    private fun showAlertDialog(title: String, message: String, buttonText: String) {
        sdk.inAppNotificationManager.showAlertDialog(
            fragmentManager = childFragmentManager,
            title = title,
            message = message,
            buttonText = buttonText
        )
    }

    private fun showFullScreenDialog(
        title: String,
        message: String,
        imageUrl: String,
        buttonNegativeText: String,
        buttonPositiveText: String
    ) {
        sdk.inAppNotificationManager.showFullScreenDialog(
            fragmentManager = childFragmentManager,
            title = title,
            message = message,
            imageUrl = imageUrl,
            buttonNegativeText = buttonNegativeText,
            buttonPositiveText = buttonPositiveText,
            onNegativeClick = { logClick("onNegativeClick") },
            onPositiveClick = { logClick("onPositiveClick") }
        )
    }

    private fun showBottomSheetDialog(
        title: String,
        message: String,
        imageUrl: String,
        buttonNegativeText: String,
        buttonPositiveText: String
    ) {
        sdk.inAppNotificationManager.showBottomSheetDialog(
            fragmentManager = childFragmentManager,
            title = title,
            message = message,
            imageUrl = imageUrl,
            buttonNegativeText = buttonNegativeText,
            buttonPositiveText = buttonPositiveText,
            onNegativeClick = { logClick("onNegativeClick") },
            onPositiveClick = { logClick("onPositiveClick") }
        )
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
        Log.d(NewFeaturesFragment::class.java.simpleName, action)
    }
}

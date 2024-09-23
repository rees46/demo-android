package rees46.demo_android.feature.home.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.personalization.SDK
import org.koin.android.ext.android.inject
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
        val debugTitle = "Привет,мы на связи"
        val debugMessage =
            "И мы к вам с хорошими новостями. Совсем скоро мы проведем вебинар по поиску на сайте — там будет масса полезной информации, которая поможет бустануть конверсию и повысить лояльность аудитории. Приходите!"
        val debugImageUrl =
            "https://ih1.redbubble.net/image.2487413451.3407/fmp,x_small,gloss,study,product,750x1000.jpg"
        val buttonNegative = "Cancel"
        val buttonPositive = "OK"
        with(binding) {

//            alertDialogButton.setOnClickListener {
//                sdk.inAppNotificationManager.showAlertDialog(
//                    fragmentManager = childFragmentManager,
//                    title = debugTitle,
//                    message = debugMessage,
//                    buttonText = buttonPositive
//                )
//            }
//
//            fullScreenDialogButton.setOnClickListener {
//                sdk.inAppNotificationManager.showFullScreenDialog(
//                    fragmentManager = childFragmentManager,
//                    title = debugTitle,
//                    message = debugMessage,
//                    imageUrl = debugImageUrl,
//                    buttonNegativeText = buttonNegative,
//                    buttonPositiveText = buttonPositive,
//                    onNegativeClick = {
//                        Log.d(requireContext().javaClass.name, ": onNegativeClick")
//                    },
//                    onPositiveClick = {
//                        Log.d(requireContext().javaClass.name, ": onPositiveClick")
//                    },
//                )
//            }
//
//            bottomSheetDialogButton.setOnClickListener {
//                sdk.inAppNotificationManager.showBottomSheetDialog(
//                    fragmentManager = childFragmentManager,
//                    title = debugTitle,
//                    message = debugMessage,
//                    imageUrl = debugImageUrl,
//                    buttonNegativeText = buttonNegative,
//                    buttonPositiveText = buttonPositive,
//                    onNegativeClick = {
//                        Log.d(requireContext().javaClass.name, ": onNegativeClick")
//                    },
//                    onPositiveClick = {
//                        Log.d(requireContext().javaClass.name, ": onPositiveClick")
//                    },
//                )
//            }
//
//            snackBarButton.setOnClickListener {
//                sdk.inAppNotificationManager.showSnackBar(
//                    view = findViewById(android.R.id.content),
//                    message = debugTitle,
//                    buttonNegativeText = buttonNegative,
//                    buttonPositiveText = buttonPositive,
//                    onNegativeClick = {
//                        Log.d(requireContext().javaClass.name, ": onNegativeClick")
//                    },
//                    onPositiveClick = {
//                        Log.d(requireContext().javaClass.name, ": onPositiveClick")
//                    },
//                )
//            }
        }
    }
}

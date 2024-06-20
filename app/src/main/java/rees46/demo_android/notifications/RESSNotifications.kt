package rees46.demo_android.notifications

import com.personalizatio.SDK

class RESSNotifications(
    private val sdk: SDK
) {

    init {
        sdk.setOnMessageListener {

        }
    }
}
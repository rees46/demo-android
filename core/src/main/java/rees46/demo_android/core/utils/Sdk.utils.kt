package rees46.demo_android.core.utils

import android.content.Context
import com.personalization.SDK
import com.personalization.api.OnApiCallbackListener
import org.json.JSONObject
import rees46.demo_android.core.settings.SdkSettings

object SdkUtils {

    fun initialize(
        sdk: SDK,
        context: Context,
        shopId: String,
    ) {
        sdk.initialize(
            context = context,
            shopId = shopId,
            shopSecretKey = "",
            apiUrl = SdkSettings.API_URL,
            preferencesKey = SdkSettings.PREFERENCES_KEY,
            tag = SdkSettings.TAG,
            stream = SdkSettings.STREAM,
            notificationType = SdkSettings.NOTIFICATION_TYPE,
            notificationId = SdkSettings.NOTIFICATION_ID
        )
    }

    fun createOnApiCallbackListener(onSuccess: () -> Unit): OnApiCallbackListener {
        return object : OnApiCallbackListener() {
            override fun onSuccess(response: JSONObject?) {
                if (isResponseSuccess(response)) {
                    onSuccess()
                }
            }
        }
    }

    private fun isResponseSuccess(response: JSONObject?): Boolean {
        return response != null
                && response.get(STATUS_RESPONSE_FIELD) == SUCCESS_RESPONSE_VALUE
    }

    private const val STATUS_RESPONSE_FIELD = "status"
    private const val SUCCESS_RESPONSE_VALUE = "success"
}

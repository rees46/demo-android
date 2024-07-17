package rees46.demo_android.core.utils

import android.content.Context
import com.personalizatio.SDK

object SdkUtils {

    fun initialize(sdk: SDK, context: Context, shopId: String? = SHOP_ID) {
        sdk.initialize(
            context = context,
            shopId = shopId ?: SHOP_ID,
            apiUrl = SDK_API_URL,
            preferencesKey = SDK_PREFERENCES_KEY,
            tag = SDK_TAG,
            stream = SDK_STREAM,
            notificationType = NOTIFICATION_TYPE,
            notificationId = NOTIFICATION_ID
        )
    }

    private const val SHOP_ID = "357382bf66ac0ce2f1722677c59511"
    private const val SDK_API_URL = "https://api.rees46.ru/"
    private const val SDK_PREFERENCES_KEY = "demo android"
    private const val SDK_TAG = "DEMO TAG"
    private const val SDK_STREAM = "android"
    private const val NOTIFICATION_TYPE = "DEMO NOTIFICATION TYPE"
    private const val NOTIFICATION_ID = "DEMO NOTIFICATION ID"
}

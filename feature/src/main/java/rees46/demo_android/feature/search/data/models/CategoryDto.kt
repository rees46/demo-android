package personaclick.demo_android.feature.search.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryDto(
    val id: String,
    val name: String,
    val parent: String?,
    val url: String,
    val count: Int
) : Parcelable

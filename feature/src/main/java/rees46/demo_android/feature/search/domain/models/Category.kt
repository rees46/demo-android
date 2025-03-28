package personaclick.demo_android.feature.search.domain.models

data class Category(
    val id: String,
    val name: String,
    val parent: String?,
    val url: String,
    val count: Int
)

package rees46.demo_android.entity.productsEntity

import android.os.Parcelable
import com.personalizatio.api.entities.product.ProductEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntity(
    val id: String,
    val name: String,
    val producerName: String,
    val price: Double?,
    val priceFormatted: String,
    val priceFull: Double?,
    val priceFullFormatted: String?,
    val pictureUrl: String,
    val description: String
) : Parcelable
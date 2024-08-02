package rees46.demo_android.feature.cart.presentation.mappers

import com.rees46.demo_android.ui.recyclerView.cart.models.CartProductItem
import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper

class CartProductItemMapper(
    private val productItemMapper: ProductItemMapper
) {

    fun toCartProductItem(cartProduct: CartProduct): CartProductItem =
        with(cartProduct) {
            CartProductItem(
                productItem = productItemMapper.toProductItem(product),
                quantity = quantity
            )
        }

    fun toCartProductItems(cartProducts: Collection<CartProduct>): List<CartProductItem> =
        cartProducts.map { toCartProductItem(it) }

    fun toCartProduct(cartProductItem: CartProductItem): CartProduct =
        with(cartProductItem) {
            CartProduct(
                product = productItemMapper.toProduct(productItem),
                quantity = quantity
            )
        }
}

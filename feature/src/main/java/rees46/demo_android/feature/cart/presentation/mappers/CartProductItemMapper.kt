package rees46.demo_android.feature.cart.presentation.mappers

import rees46.demo_android.feature.cart.presentation.models.CartProductRecyclerViewItem
import rees46.demo_android.feature.cart.domain.models.CartProduct
import rees46.demo_android.feature.products.presentation.mappers.ProductItemMapper

class CartProductItemMapper(
    private val productItemMapper: ProductItemMapper
) {

    fun toCartProductItem(cartProduct: CartProduct): CartProductRecyclerViewItem =
        with(cartProduct) {
            CartProductRecyclerViewItem(
                productItem = productItemMapper.toProductItem(product),
                quantity = quantity
            )
        }

    fun toCartProductItems(cartProducts: Collection<CartProduct>): List<CartProductRecyclerViewItem> =
        cartProducts.map { toCartProductItem(it) }

    fun toCartProduct(cartProductItem: CartProductRecyclerViewItem): CartProduct =
        with(cartProductItem) {
            CartProduct(
                product = productItemMapper.toProduct(productItem),
                quantity = quantity
            )
        }
}

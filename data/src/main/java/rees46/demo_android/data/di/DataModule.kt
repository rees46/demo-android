package rees46.demo_android.data.di

import org.koin.dsl.module
import rees46.demo_android.data.repository.cart.CartRepositoryImpl
import rees46.demo_android.data.repository.cart.Cart
import rees46.demo_android.domain.repository.CartRepository
import rees46.demo_android.domain.usecase.cart.AddProductToCartUseCase
import rees46.demo_android.domain.usecase.cart.GetCartProductUseCase
import rees46.demo_android.domain.usecase.cart.GetCartProductsUseCase
import rees46.demo_android.domain.usecase.cart.GetCartSumPriceUseCase
import rees46.demo_android.domain.usecase.cart.RemoveProductFromCartUseCase

object DataModule {

    val cartModule = module {
        single<CartRepository> {
            CartRepositoryImpl(
                sdk = get(),
                cart = Cart()
            )
        }
        single {
            GetCartProductsUseCase(
                cartRepository = get()
            )
        }
        single {
            GetCartProductUseCase(
                cartRepository = get()
            )
        }
        single {
            AddProductToCartUseCase(
                cartRepository = get()
            )
        }
        single {
            RemoveProductFromCartUseCase(
                cartRepository = get()
            )
        }
        single {
            GetCartSumPriceUseCase(
                cartRepository = get()
            )
        }
    }
}

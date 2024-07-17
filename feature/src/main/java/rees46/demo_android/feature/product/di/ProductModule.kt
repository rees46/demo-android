package rees46.demo_android.feature.product.di

import org.koin.dsl.module
import rees46.demo_android.feature.product.data.repository.ProductRepositoryImpl
import rees46.demo_android.feature.product.domain.repository.ProductRepository

val productModule = module {
    single<ProductRepository> {
        ProductRepositoryImpl(
            sdk = get()
        )
    }
}

package rees46.demo_android.feature.card_product.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rees46.demo_android.feature.card_product.presentation.CardProductViewModel

object Module {

    val cardProductFragmentModule = module {
        viewModel { parameter ->
            CardProductViewModel(
                sdk = get(),
                product = parameter.get()
            )
        }
    }
}

package ir.dehghani.kotlincrypto.di.module

import ir.dehghani.kotlincrypto.views.main.presenter.MainPageVMP
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainPageVMP(get(), get()) }
}

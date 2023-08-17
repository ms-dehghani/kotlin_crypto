package ir.dehghani.kotlincrypto.di.module

import ir.dehghani.kotlincrypto.model.items.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.model.items.currency.list.state.CurrencyListState
import org.koin.dsl.module


val stateModule = module {
    single {  CurrencyItemState }
    single {  CurrencyListState }
}
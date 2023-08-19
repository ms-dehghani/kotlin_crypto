package ir.dehghani.kotlincrypto.di.module

import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.items.currency.item.presenter.CurrencyItemPresenter
import ir.dehghani.kotlincrypto.model.items.currency.list.model.CurrencyListModel
import ir.dehghani.kotlincrypto.model.items.currency.list.presenter.CurrencyListPresenter
import ir.dehghani.kotlincrypto.model.repository.Repository
import org.koin.dsl.module


val presenterModule = module {
    single {  CurrencyListPresenter.getInstance(CurrencyListModel.getInstance(getKoin().get<Repository>())) }
    single {  CurrencyItemPresenter.getInstance(CurrencyItemModel.getInstance(getKoin().get<Repository>())) }
}
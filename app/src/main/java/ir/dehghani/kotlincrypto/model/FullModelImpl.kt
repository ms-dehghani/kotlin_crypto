package ir.dehghani.kotlincrypto.model

import ir.dehghani.kotlincrypto.model.items.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.list.CurrencyListModelImpl

interface FullModelImpl: CurrencyListModelImpl, CurrencyItemModelImpl {
}
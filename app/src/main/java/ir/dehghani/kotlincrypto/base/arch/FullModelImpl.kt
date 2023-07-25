package ir.dehghani.kotlincrypto.base.arch

import ir.dehghani.kotlincrypto.base.arch.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.base.arch.currency.list.CurrencyListModelImpl

interface FullModelImpl: CurrencyListModelImpl , CurrencyItemModelImpl {
}
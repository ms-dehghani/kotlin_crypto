package ir.dehghani.kotlincrypto.base.arch.currency.list

import ir.dehghani.kotlincrypto.pojo.CurrencyItem

interface CurrencyListModelImpl {

    fun getAllCurrency() : List<CurrencyItem>

}
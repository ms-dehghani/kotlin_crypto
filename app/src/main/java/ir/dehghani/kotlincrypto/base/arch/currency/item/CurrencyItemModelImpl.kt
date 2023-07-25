package ir.dehghani.kotlincrypto.base.arch.currency.item

import ir.dehghani.kotlincrypto.pojo.CurrencyItem

interface CurrencyItemModelImpl {

    fun getCurrency(ID : String) : CurrencyItem

}
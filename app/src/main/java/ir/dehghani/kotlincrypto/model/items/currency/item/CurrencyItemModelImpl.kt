package ir.dehghani.kotlincrypto.model.items.currency.item

import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc

interface CurrencyItemModelImpl {

    fun getCurrency(ID : String,repoMiddlewareFunc: RepoMiddlewareFunc = RepoMiddlewareFunc()) : CurrencyItem

}
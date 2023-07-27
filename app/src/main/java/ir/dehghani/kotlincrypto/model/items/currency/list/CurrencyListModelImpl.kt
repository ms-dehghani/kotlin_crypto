package ir.dehghani.kotlincrypto.model.items.currency.list

import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc

interface CurrencyListModelImpl {

    fun getAllCurrency(repoMiddlewareFunc: RepoMiddlewareFunc = RepoMiddlewareFunc()) : List<CurrencyItem>

}
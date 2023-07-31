package ir.dehghani.kotlincrypto.model.items.currency.item

import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback

interface CurrencyItemModelImpl {

    fun getCurrency(ID : String,repoMiddlewareFunc: RepoMiddlewareFunc = RepoMiddlewareFunc(), result: RepoResultCallback<CurrencyItem>)

}
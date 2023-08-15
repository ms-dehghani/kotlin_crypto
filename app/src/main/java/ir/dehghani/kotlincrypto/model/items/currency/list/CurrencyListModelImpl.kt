package ir.dehghani.kotlincrypto.model.items.currency.list

import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback

interface CurrencyListModelImpl {

    fun getAllCurrency( result : RepoResultCallback<List<CurrencyItem>>)

}
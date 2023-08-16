package ir.dehghani.kotlincrypto.model.items.currency.item

import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback

interface CurrencyItemModelImpl {

    suspend fun getCurrency(ID : String, result: RepoResultCallback<CurrencyItem>)

}
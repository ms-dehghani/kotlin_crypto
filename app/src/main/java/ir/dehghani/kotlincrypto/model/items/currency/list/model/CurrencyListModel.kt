package ir.dehghani.kotlincrypto.model.items.currency.list.model

import ir.dehghani.kotlincrypto.base.arch.BaseModel
import ir.dehghani.kotlincrypto.model.items.currency.list.CurrencyListModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyListModel @Inject constructor(private val repo: CurrencyListModelImpl) : BaseModel() {

    suspend fun getAllCurrency(result: RepoResultCallback<List<CurrencyItem>>) {
        repo.getAllCurrency(result)
    }

}
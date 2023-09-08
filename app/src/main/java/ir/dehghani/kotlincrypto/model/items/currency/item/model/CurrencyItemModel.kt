package ir.dehghani.kotlincrypto.model.items.currency.item.model

import ir.dehghani.kotlincrypto.base.arch.BaseModel
import ir.dehghani.kotlincrypto.model.items.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyItemModel @Inject constructor(private var repo: CurrencyItemModelImpl) : BaseModel() {

    suspend fun getCurrency(ID: String, result: RepoResultCallback<CurrencyItem>) {
        repo.getCurrency(ID, result)
    }

}
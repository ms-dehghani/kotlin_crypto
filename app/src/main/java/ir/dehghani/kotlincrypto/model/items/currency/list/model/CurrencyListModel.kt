package ir.dehghani.kotlincrypto.model.items.currency.list.model

import ir.dehghani.kotlincrypto.base.arch.base.BaseModel
import ir.dehghani.kotlincrypto.model.items.currency.list.CurrencyListModelImpl
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback

class CurrencyListModel(private val repo: CurrencyListModelImpl) : BaseModel() {

    companion object {

        @Volatile
        private var instance: CurrencyListModel? = null

        fun getInstance(repo: CurrencyListModelImpl) =
            instance ?: synchronized(this) {
                instance ?: CurrencyListModel(repo).also { instance = it }
            }
    }

    fun getAllCurrency(repoMiddlewareFunc: RepoMiddlewareFunc = RepoMiddlewareFunc(), result: RepoResultCallback<List<CurrencyItem>>) {
        repo.getAllCurrency(repoMiddlewareFunc, result)
    }

}
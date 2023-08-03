package ir.dehghani.kotlincrypto.model.items.currency.item.model

import ir.dehghani.kotlincrypto.base.arch.BaseModel
import ir.dehghani.kotlincrypto.model.items.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback

class CurrencyItemModel private constructor(private val repo: CurrencyItemModelImpl) : BaseModel() {

    companion object {

        @Volatile
        private var instance: CurrencyItemModel? = null

        fun getInstance(repo: CurrencyItemModelImpl): CurrencyItemModel =
            instance ?: synchronized(this) {
                instance ?: CurrencyItemModel(repo).also { instance = it }
            }
    }

    fun getCurrency(ID: String, result: RepoResultCallback<CurrencyItem>) {
        repo.getCurrency(ID, result)
    }

}
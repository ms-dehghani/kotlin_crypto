package ir.dehghani.kotlincrypto.base.arch.currency.list.model

import ir.dehghani.kotlincrypto.base.arch.base.BaseModel
import ir.dehghani.kotlincrypto.base.arch.currency.list.CurrencyListModelImpl
import ir.dehghani.kotlincrypto.pojo.CurrencyItem

class CurrencyListModel(private val repo: CurrencyListModelImpl) : BaseModel() {

    companion object {

        @Volatile
        private var instance: CurrencyListModel? = null

        fun getInstance(repo: CurrencyListModelImpl) =
            instance ?: synchronized(this) {
                instance ?: CurrencyListModel(repo).also { instance = it }
            }
    }

    fun getAllCurrency(): List<CurrencyItem> {
        return repo.getAllCurrency()
    }

}
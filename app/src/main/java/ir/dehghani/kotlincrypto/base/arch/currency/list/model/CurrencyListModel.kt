package ir.dehghani.kotlincrypto.base.arch.currency.list.model

import ir.dehghani.kotlincrypto.base.arch.base.BaseModel
import ir.dehghani.kotlincrypto.base.arch.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.base.arch.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.base.arch.currency.list.CurrencyListModelImpl
import ir.dehghani.kotlincrypto.base.arch.currency.list.state.CurrencyListState
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import kotlinx.coroutines.delay

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
        val time = System.currentTimeMillis()
        while(System.currentTimeMillis() < time + 2000)
            continue
        return repo.getAllCurrency()
    }

}
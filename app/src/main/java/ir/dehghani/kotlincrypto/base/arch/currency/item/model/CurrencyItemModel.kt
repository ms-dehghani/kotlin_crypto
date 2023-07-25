package ir.dehghani.kotlincrypto.base.arch.currency.item.model

import ir.dehghani.kotlincrypto.base.arch.base.BaseModel
import ir.dehghani.kotlincrypto.base.arch.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.base.arch.currency.item.presenter.CurrencyItemPresenter
import ir.dehghani.kotlincrypto.base.arch.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.base.arch.currency.list.CurrencyListModelImpl
import ir.dehghani.kotlincrypto.base.arch.currency.list.model.CurrencyListModel
import ir.dehghani.kotlincrypto.pojo.CurrencyItem

class CurrencyItemModel private constructor(private val repo: CurrencyItemModelImpl) : BaseModel() {

    companion object {

        @Volatile
        private var instance: CurrencyItemModel? = null

        fun getInstance(repo: CurrencyItemModelImpl) =
            instance ?: synchronized(this) {
                instance ?: CurrencyItemModel(repo).also { instance = it }
            }
    }

    fun getCurrency(ID: String): CurrencyItem {
        return repo.getCurrency(ID);
    }

}
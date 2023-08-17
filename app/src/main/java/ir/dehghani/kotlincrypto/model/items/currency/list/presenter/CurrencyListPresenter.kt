package ir.dehghani.kotlincrypto.model.items.currency.list.presenter

import ir.dehghani.kotlincrypto.base.arch.BasePresenter
import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.items.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.model.items.currency.list.model.CurrencyListModel
import ir.dehghani.kotlincrypto.model.items.currency.list.state.CurrencyListState
import ir.dehghani.kotlincrypto.model.repository.Repository
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.utils.runOnBackground
import java.lang.Exception

class CurrencyListPresenter private constructor(
    model: CurrencyListModel, state: CurrencyListState
) : BasePresenter<CurrencyListModel, CurrencyListState>(model, state) {

    companion object {

        @Volatile
        private var instance: CurrencyListPresenter? = null

        fun getInstance(model: CurrencyListModel, state: CurrencyListState) =
            instance ?: synchronized(this) {
                instance ?: CurrencyListPresenter(model, state).also { instance = it }
            }
    }


    fun getAllCurrency() {
        runOnBackground({
            val result = object : RepoResultCallback<List<CurrencyItem>> {
                override fun onError(ex: Exception) {
                }

                override fun onResponse(data: List<CurrencyItem>) {
                    getState().getItemList().postValue(data)
                }
            }
            getModel().getAllCurrency(result)
        }, this::getAllCurrency.name)
    }


}
package ir.dehghani.kotlincrypto.model.items.currency.list.presenter

import ir.dehghani.kotlincrypto.base.arch.base.BasePresenter
import ir.dehghani.kotlincrypto.model.items.currency.list.model.CurrencyListModel
import ir.dehghani.kotlincrypto.model.items.currency.list.state.CurrencyListState
import ir.dehghani.kotlincrypto.model.repository.Repository
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.utils.runOnBackground
import java.lang.Exception

class CurrencyListPresenter private constructor(
    model: CurrencyListModel = CurrencyListModel.getInstance(Repository.getInstance()),
    state: CurrencyListState = CurrencyListState
) : BasePresenter<CurrencyListModel, CurrencyListState>(model, state) {

    companion object {

        @Volatile
        private var instance: CurrencyListPresenter? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CurrencyListPresenter().also { instance = it }
            }
    }


    fun getAllCurrency(repoMiddlewareFunc: RepoMiddlewareFunc = RepoMiddlewareFunc()) {
        runOnBackground({
            val result = object : RepoResultCallback<List<CurrencyItem>> {
                override fun onError(ex: Exception) {
                }

                override fun onResponse(data: List<CurrencyItem>) {
                    getState().getItemList().postValue(data)
                }
            }
            getModel().getAllCurrency(repoMiddlewareFunc, result)
        }, this::getAllCurrency.name)
    }


}
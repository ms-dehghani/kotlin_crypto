package ir.dehghani.kotlincrypto.model.items.currency.item.presenter

import ir.dehghani.kotlincrypto.base.arch.base.BasePresenter
import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.items.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.model.repository.Repository
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.utils.runOnBackground
import java.lang.Exception

class CurrencyItemPresenter private constructor(
    model: CurrencyItemModel = CurrencyItemModel.getInstance(Repository.getInstance()),
    state: CurrencyItemState = CurrencyItemState
) :
    BasePresenter<CurrencyItemModel, CurrencyItemState>(model, state) {

    companion object {

        @Volatile
        private var instance: CurrencyItemPresenter? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CurrencyItemPresenter().also { instance = it }
            }
    }


    fun getCurrency(ID: String, repoMiddlewareFunc: RepoMiddlewareFunc = RepoMiddlewareFunc()) {
        runOnBackground({
            val result = object : RepoResultCallback<CurrencyItem> {
                override fun onError(ex: Exception) {
                }

                override fun onResponse(detail: CurrencyItem) {
                    getState().getItemDetail().postValue(detail)
                }
            }
            getModel().getCurrency(ID, repoMiddlewareFunc, result)
        }, this::getCurrency.name)
    }


}
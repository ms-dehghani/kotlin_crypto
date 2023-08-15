package ir.dehghani.kotlincrypto.model.items.currency.item.presenter

import ir.dehghani.kotlincrypto.base.arch.BasePresenter
import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.items.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.model.repository.Repository
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.utils.runOnBackground
import java.lang.Exception

class CurrencyItemPresenter private constructor(model: CurrencyItemModel, state: CurrencyItemState) :
    BasePresenter<CurrencyItemModel, CurrencyItemState>(model, state) {

    companion object {

        @Volatile
        private var instance: CurrencyItemPresenter? = null

        fun getInstance(
            model: CurrencyItemModel = CurrencyItemModel.getInstance(Repository.getInstance()),
            state: CurrencyItemState = CurrencyItemState
        ) =
            instance ?: synchronized(this) {
                instance ?: CurrencyItemPresenter(model, state).also { instance = it }
            }
    }


    fun getCurrency(ID: String) {
        runOnBackground({
            val result = object : RepoResultCallback<CurrencyItem> {
                override fun onError(ex: Exception) {
                }

                override fun onResponse(detail: CurrencyItem) {
                    getState().getItemDetail().postValue(detail)
                }
            }
            getModel().getCurrency(ID, result)
        }, this::getCurrency.name)
    }


}
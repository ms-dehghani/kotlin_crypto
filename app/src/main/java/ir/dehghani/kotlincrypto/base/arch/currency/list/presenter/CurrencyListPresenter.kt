package ir.dehghani.kotlincrypto.base.arch.currency.list.presenter

import ir.dehghani.kotlincrypto.base.arch.base.BasePresenter
import ir.dehghani.kotlincrypto.base.arch.currency.list.model.CurrencyListModel
import ir.dehghani.kotlincrypto.base.arch.currency.list.state.CurrencyListState
import ir.dehghani.kotlincrypto.repository.Repository
import ir.dehghani.kotlincrypto.utils.RunOnBackground

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


    fun getAllCurrency() {
        println("run getAllCurrency")
        RunOnBackground {
            println("in background")
            val dataList = getModel().getAllCurrency()
            getState().getItemList().postValue(dataList)
            println("end background")
        }

    }


}
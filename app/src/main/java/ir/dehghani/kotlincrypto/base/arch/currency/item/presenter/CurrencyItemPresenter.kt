package ir.dehghani.kotlincrypto.base.arch.currency.item.presenter

import ir.dehghani.kotlincrypto.base.arch.base.BasePresenter
import ir.dehghani.kotlincrypto.base.arch.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.base.arch.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.repository.Repository
import ir.dehghani.kotlincrypto.utils.RunOnBackground

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


    fun getCurrency(ID: String) {
        RunOnBackground {
            val detail = getModel().getCurrency(ID)
            getState().getItemDetail().postValue(detail)
        }
    }


}
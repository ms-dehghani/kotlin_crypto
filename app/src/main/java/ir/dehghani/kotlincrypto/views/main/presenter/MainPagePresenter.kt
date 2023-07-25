package ir.dehghani.kotlincrypto.views.main.presenter

import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.currency.item.presenter.CurrencyItemPresenter
import ir.dehghani.kotlincrypto.base.arch.currency.list.presenter.CurrencyListPresenter
import ir.dehghani.kotlincrypto.pojo.CurrencyItem

class MainPagePresenter(
    private var currencyListPresenter: CurrencyListPresenter = CurrencyListPresenter.getInstance(),
    private var currencyItemPresenter: CurrencyItemPresenter = CurrencyItemPresenter.getInstance()
) {


    fun getAllCurrency() {
        currencyListPresenter.getAllCurrency()
    }

    fun getCurrencyListLiveData(): MutableLiveData<List<CurrencyItem>>{
        return currencyListPresenter.state.itemList
    }

    fun getCurrencyDetail(ID: String) {
        currencyItemPresenter.getCurrency(ID)
    }

    fun getCurrencyItemLiveData(): MutableLiveData<CurrencyItem>{
        return currencyItemPresenter.state.itemDetail
    }



}
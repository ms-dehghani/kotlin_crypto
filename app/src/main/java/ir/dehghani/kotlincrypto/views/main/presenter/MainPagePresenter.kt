package ir.dehghani.kotlincrypto.views.main.presenter

import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.model.items.currency.item.presenter.CurrencyItemPresenter
import ir.dehghani.kotlincrypto.model.items.currency.list.presenter.CurrencyListPresenter
import ir.dehghani.kotlincrypto.pojo.CurrencyItem

class MainPagePresenter(
    private var currencyListPresenter: CurrencyListPresenter = CurrencyListPresenter.getInstance(),
    private var currencyItemPresenter: CurrencyItemPresenter = CurrencyItemPresenter.getInstance()
) : MainPagePresenterContract() {


    override fun getAllCurrency() {
        currencyListPresenter.getAllCurrency()
    }

    override fun getCurrencyListLiveData(): MutableLiveData<List<CurrencyItem>>{
        return currencyListPresenter.getState().getItemList()
    }

    override fun getCurrencyDetail(ID: String) {
        currencyItemPresenter.getCurrency(ID)
    }

    override fun getCurrencyItemLiveData(): MutableLiveData<CurrencyItem>{
        return currencyItemPresenter.getState().getItemDetail()
    }



}
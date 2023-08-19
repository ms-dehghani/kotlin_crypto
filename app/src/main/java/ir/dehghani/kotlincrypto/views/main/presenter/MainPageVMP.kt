package ir.dehghani.kotlincrypto.views.main.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ir.dehghani.kotlincrypto.model.items.currency.item.presenter.CurrencyItemPresenter
import ir.dehghani.kotlincrypto.model.items.currency.list.presenter.CurrencyListPresenter
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem

class MainPageVMP(
    private var currencyListPresenter: CurrencyListPresenter,
    private var currencyItemPresenter: CurrencyItemPresenter
) : MainPageVMPContract() {


    val itemLiveData = MutableLiveData<CurrencyItem>()
    val listLiveData = MutableLiveData<List<CurrencyItem>>()

    override fun getAllCurrency() {
        currencyListPresenter.getAllCurrency().observeForever {
            listLiveData.postValue(it)
        }
    }

    override fun getCurrencyListLiveData(): MutableLiveData<List<CurrencyItem>> {
        return listLiveData
    }

    override fun getCurrencyDetail(ID: String) {
        currencyItemPresenter.getCurrency(ID).observeForever {
            itemLiveData.postValue(it)
        }
    }

    override fun getCurrencyItemLiveData(): MutableLiveData<CurrencyItem> {
        return itemLiveData
    }


}
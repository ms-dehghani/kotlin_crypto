package ir.dehghani.kotlincrypto.views.main.presenter

import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.BaseViewModel
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem

abstract class MainPageVMPContract : BaseViewModel() {

    abstract fun getAllCurrency()

    abstract fun getCurrencyListLiveData(): MutableLiveData<List<CurrencyItem>>

    abstract fun getCurrencyDetail(ID: String)

    abstract fun getCurrencyItemLiveData(): MutableLiveData<CurrencyItem>

}
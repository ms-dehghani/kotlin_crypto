package ir.dehghani.kotlincrypto.views.main.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.dehghani.kotlincrypto.base.arch.base.BaseViewModel
import ir.dehghani.kotlincrypto.pojo.CurrencyItem

abstract class MainPagePresenterContract : BaseViewModel() {

    abstract fun getAllCurrency()

    abstract fun getCurrencyListLiveData(): MutableLiveData<List<CurrencyItem>>

    abstract fun getCurrencyDetail(ID: String)

    abstract fun getCurrencyItemLiveData(): MutableLiveData<CurrencyItem>

}
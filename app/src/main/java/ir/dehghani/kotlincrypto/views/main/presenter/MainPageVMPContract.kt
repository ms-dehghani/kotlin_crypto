package ir.dehghani.kotlincrypto.views.main.presenter

import androidx.compose.runtime.State
import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.BaseViewModel
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.views.main.state.MainPageState

abstract class MainPageVMPContract : BaseViewModel() {

    abstract fun getAllCurrency()

    abstract fun getCurrencyDetail(ID: String)

    abstract fun getPageState(): State<MainPageState>

}
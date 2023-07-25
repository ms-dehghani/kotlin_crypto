package ir.dehghani.kotlincrypto.base.arch.currency.list.state

import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.base.BaseState
import ir.dehghani.kotlincrypto.pojo.CurrencyItem

object CurrencyListState : BaseState() {

    private val itemList: MutableLiveData<List<CurrencyItem>> = MutableLiveData()

    fun getItemList() = itemList

}
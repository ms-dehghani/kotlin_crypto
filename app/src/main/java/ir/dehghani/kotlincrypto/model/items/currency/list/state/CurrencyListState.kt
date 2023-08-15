package ir.dehghani.kotlincrypto.model.items.currency.list.state

import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.BaseState
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem

object CurrencyListState : BaseState() {

    private val itemList: MutableLiveData<List<CurrencyItem>> = MutableLiveData()

    fun getItemList() = itemList

}
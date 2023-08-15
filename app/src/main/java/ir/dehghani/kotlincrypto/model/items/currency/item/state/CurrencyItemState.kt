package ir.dehghani.kotlincrypto.model.items.currency.item.state

import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.BaseState
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem

object CurrencyItemState : BaseState() {

    private val itemDetail: MutableLiveData<CurrencyItem> = MutableLiveData()

    fun getItemDetail() = itemDetail
}
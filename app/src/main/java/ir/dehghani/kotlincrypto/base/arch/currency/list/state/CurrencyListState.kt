package ir.dehghani.kotlincrypto.base.arch.currency.list.state

import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.base.BaseState
import ir.dehghani.kotlincrypto.base.arch.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.pojo.CurrencyItem

class CurrencyListState private constructor(val itemList: MutableLiveData<List<CurrencyItem>> = MutableLiveData()) : BaseState() {

    companion object {

        @Volatile
        private var instance: CurrencyListState? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CurrencyListState().also { instance = it }
            }
    }

}
package ir.dehghani.kotlincrypto.base.arch.currency.item.state

import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.base.BaseState
import ir.dehghani.kotlincrypto.pojo.CurrencyItem

class CurrencyItemState private constructor(val itemDetail: MutableLiveData<CurrencyItem> = MutableLiveData()) : BaseState() {


    companion object {

        @Volatile
        private var instance: CurrencyItemState? = null
        
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CurrencyItemState().also { instance = it }
            }
    }

}
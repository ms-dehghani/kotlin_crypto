package ir.dehghani.kotlincrypto.views.main.presenter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import ir.dehghani.kotlincrypto.model.items.currency.item.presenter.CurrencyItemPresenter
import ir.dehghani.kotlincrypto.model.items.currency.list.presenter.CurrencyListPresenter
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.views.main.state.MainPageState

class MainPageVMP(
    private var currencyListPresenter: CurrencyListPresenter,
    private var currencyItemPresenter: CurrencyItemPresenter
) : MainPageVMPContract() {

    private val _state = mutableStateOf(MainPageState())


    val itemLiveData = MutableLiveData<CurrencyItem>()

    override fun getAllCurrency() {
        _state.value = _state.value.copy(true)
        currencyListPresenter.getAllCurrency().observeForever {
//            listLiveData.postValue(it)
            _state.value = _state.value.copy(false, it)
        }
    }

    override fun getCurrencyDetail(ID: String) {
        currencyItemPresenter.getCurrency(ID).observeForever {
            itemLiveData.postValue(it)
        }
    }

    override fun getPageState(): State<MainPageState> {
        return _state
    }


}
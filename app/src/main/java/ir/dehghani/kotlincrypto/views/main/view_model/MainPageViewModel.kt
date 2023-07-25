package ir.dehghani.kotlincrypto.views.main.view_model

import androidx.lifecycle.ViewModel
import ir.dehghani.kotlincrypto.base.arch.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.base.arch.currency.list.state.CurrencyListState
import ir.dehghani.kotlincrypto.views.main.presenter.MainPagePresenter

class MainPageViewModel(var mainPagePresenter: MainPagePresenter) : ViewModel() {

    var listState: CurrencyListState = CurrencyListState.getInstance()
    var itemState: CurrencyItemState = CurrencyItemState.getInstance()

    fun getCurrencyList(){
        mainPagePresenter.getAllCurrency();
    }

    fun getCurrencyDetail(ID : String){
        mainPagePresenter.getCurrencyDetail(ID)
    }

}
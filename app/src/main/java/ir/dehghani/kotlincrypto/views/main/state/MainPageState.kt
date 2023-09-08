package ir.dehghani.kotlincrypto.views.main.state

import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem

data class MainPageState(val isLoading:Boolean = true , val items : List<CurrencyItem> = emptyList() )

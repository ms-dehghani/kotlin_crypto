package ir.dehghani.kotlincrypto.repository.api

import ir.dehghani.kotlincrypto.base.arch.FullModelImpl
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.pojo.currencyItemEmpty
import ir.dehghani.kotlincrypto.repository.api.utility.RequestTypeEnum
import ir.dehghani.kotlincrypto.repository.api.utility.WebserviceCaller
import ir.dehghani.kotlincrypto.repository.api.utility.WebserviceMiddleware
import java.util.ArrayList

@Suppress("UNCHECKED_CAST")
class WebserviceRepo(private val serviceMiddleware: WebserviceMiddleware, private val webserviceCaller: WebserviceCaller) : FullModelImpl {

    override fun getAllCurrency(): List<CurrencyItem> {
        val result = serviceMiddleware.Call(callingFunction = { webserviceCaller.call("", "", RequestTypeEnum.Get) })
        if(result is List<*>)
            return result as List<CurrencyItem>
        return ArrayList<CurrencyItem>()
    }

    override fun getCurrency(ID: String): CurrencyItem {
        val result = serviceMiddleware.Call(callingFunction = { webserviceCaller.call("", "", RequestTypeEnum.Get) })
        if(result is CurrencyItem)
            return result
        return currencyItemEmpty()
    }
}